package org.codelab.graacc.Orquestrador.service;

import org.codelab.graacc.Orquestrador.dto.appointment.AppointmentDTO;
import org.codelab.graacc.Orquestrador.dto.notification.NotificationDTO;
import org.codelab.graacc.Orquestrador.dto.patient.PatientDTO;
import org.codelab.graacc.Orquestrador.integration.AppointmentClient;
import org.codelab.graacc.Orquestrador.integration.NotificationClient;
import org.codelab.graacc.Orquestrador.mapper.AppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private AppointmentClient appointmentClient;

    @Autowired
    private NotificationClient notificationClient;

    public List<AppointmentDTO> listarTodosAgendamentos(String headerAuthorization) {
        return appointmentClient.obterListaAgendamentosDoUsuario(headerAuthorization);
    }

    public AppointmentDTO listarAgendamentoEspecifico(String headerAuthorization, Long idAppointment) {
        return appointmentClient.listarAgendamentoEspecifico(headerAuthorization, idAppointment);
    }

    public List<NotificationDTO> listarTodasNotificacoes(String headerAuthorization) {
        List<AppointmentDTO> listAppointments = listarTodosAgendamentos(headerAuthorization);
        var appointmentsInfo = AppointmentMapper.toAppointmentInfoList(listAppointments);
        List<NotificationDTO> notificationDTOs =
                notificationClient.obterListaNotificacoesNaoLidasDoUsuario(headerAuthorization, appointmentsInfo);
        return notificationDTOs;
    }

    public List<NotificationDTO> listarNotificacoesDeUmAgendamento(String headerAuthorization, Long idAppointment) {
        return notificationClient.obterListaNotificacoesDeUmAgendamentoEspecifico(headerAuthorization, idAppointment);
    }

    public NotificationDTO marcarNotificacaoComoLida(String headerAuthorization, Long idNotificacao) {
        return notificationClient.marcarNotificacaoComoLida(headerAuthorization, idNotificacao);
    }

    public PatientDTO listarPacienteEspecifico(String headerAuthorization, Long idPaciente) {
        List<PatientDTO> listPatients = appointmentClient.listarTodosPacientes(headerAuthorization);
        for(int i = 0; i < listPatients.size(); i++) {
          PatientDTO currentPatient = listPatients.get(i);
          if(currentPatient.getIdPaciente() == idPaciente)
            return currentPatient;
        }
        return listPatients.get(0);
    }
}
