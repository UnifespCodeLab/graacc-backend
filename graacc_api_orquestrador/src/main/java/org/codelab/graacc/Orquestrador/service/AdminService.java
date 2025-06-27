package org.codelab.graacc.Orquestrador.service;

import org.codelab.graacc.Orquestrador.dto.appointment.AppointmentDTO;
import org.codelab.graacc.Orquestrador.dto.appointment.AppointmentInfoDTO;
import org.codelab.graacc.Orquestrador.dto.appointment.AppointmentNotificationResponse;
import org.codelab.graacc.Orquestrador.dto.appointment.AppointmentRequest;
import org.codelab.graacc.Orquestrador.dto.notification.NotificationDTO;
import org.codelab.graacc.Orquestrador.integration.AppointmentClient;
import org.codelab.graacc.Orquestrador.integration.NotificationClient;
import org.codelab.graacc.Orquestrador.mapper.AppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    AppointmentClient appointmentClient;

    @Autowired
    NotificationClient notificationClient;

    public AppointmentNotificationResponse adicionarAgendamento(String token, AppointmentRequest request) {
        AppointmentDTO appointment = appointmentClient.adicionarAgendamento(token, request);
        List<NotificationDTO> listNotifications = new ArrayList<>();
        if (appointment != null) {
            AppointmentInfoDTO appointmentInfoDTO = AppointmentMapper.toAppointmentInfo(appointment);
            listNotifications = notificationClient.adicionarNotificacoes(token, appointmentInfoDTO);
        }
        return new AppointmentNotificationResponse(appointment, listNotifications);
    }

    public List<AppointmentNotificationResponse> adicionarConjuntoAgendamentos(String token, List<AppointmentRequest> request) {
        List<AppointmentDTO> listAppointmentsAdded = new ArrayList<>();
        List<NotificationDTO> listNotifications = new ArrayList<>();

        // adiciona cada agendamento
        for(AppointmentRequest appointmentRequest: request) {
            AppointmentDTO appointment = appointmentClient.adicionarAgendamento(token, appointmentRequest);
            listAppointmentsAdded.add(appointment);
        }
        // adiciona notificacoes para o conjunto de agendamentos
        if (!listAppointmentsAdded.isEmpty()) {
            List<AppointmentInfoDTO> appointmentInfoList = AppointmentMapper.toAppointmentInfoList(listAppointmentsAdded);
            listNotifications = notificationClient.adicionarNotificacoesConjunto(token, appointmentInfoList);
        }
        Map<Long, List<NotificationDTO>> notificacoesPorAgendamento = listNotifications.stream().collect(Collectors.groupingBy(NotificationDTO::getIdAgendamento));
        // monta objeto de resposta
        List<AppointmentNotificationResponse> resposta = new ArrayList<>();
        for(AppointmentDTO appointmentDTO: listAppointmentsAdded) {
            AppointmentNotificationResponse item = new AppointmentNotificationResponse();
            item.setAgendamento(appointmentDTO);
            item.setNotificacoes(notificacoesPorAgendamento.get(appointmentDTO.getIdAgendamento()));
            resposta.add(item);
        }
        return resposta;
    }
}
