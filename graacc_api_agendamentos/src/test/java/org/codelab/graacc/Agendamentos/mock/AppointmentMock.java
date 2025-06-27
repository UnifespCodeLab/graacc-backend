package org.codelab.graacc.Agendamentos.mock;

import org.codelab.graacc.Agendamentos.dto.request.AppointmentRequestDTO;
import org.codelab.graacc.Agendamentos.dto.response.AppointmentResponseDTO;
import org.codelab.graacc.Agendamentos.entity.AppointmentEntity;
import org.codelab.graacc.Agendamentos.mapper.AppointmentMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentMock {

    public static AppointmentResponseDTO appointmentResponseMock() {
        AppointmentResponseDTO appointment = new AppointmentResponseDTO();
        appointment.setIdAgendamento(1L);
        appointment.setTitulo("Consulta D");
        appointment.setDescricao("Doutor Carlos Miguel");
        appointment.setData("2024-01-21 15:00:00");
        appointment.setLocal("Sala 3 - Predio A");
        appointment.setIdPaciente(1L);
        return appointment;
    }

    public static AppointmentEntity appointmentEntityMock() {
        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setIdAgendamento(1L);
        appointment.setTitulo("Consulta D");
        appointment.setDescricao("Doutor Carlos Miguel");
        appointment.setData(LocalDateTime.now());
        appointment.setLocal("Sala 3 - Predio A");
        appointment.setIdPaciente(1L);
        return appointment;
    }

    public static List<AppointmentEntity> appointmentListEntityMock() {
        AppointmentEntity appointment1 = appointmentEntityMock();
        AppointmentEntity appointment2 = new AppointmentEntity();
        appointment2.setIdAgendamento(2L);
        appointment2.setTitulo("Consulta D");
        appointment2.setDescricao("Doutora Ana Carolina");
        appointment2.setData(LocalDateTime.now());
        appointment2.setLocal("Sala 4 - Predio B");
        appointment2.setIdPaciente(2L);
        return List.of(appointment1, appointment2);
    }

    public static List<AppointmentEntity> appointmentListEntityOfSameUserMock() {
        AppointmentEntity appointment1 = appointmentEntityMock();
        AppointmentEntity appointment2 = new AppointmentEntity();
        appointment2.setIdAgendamento(2L);
        appointment2.setTitulo("Consulta D");
        appointment2.setDescricao("Doutora Ana Carolina");
        appointment2.setData(LocalDateTime.now());
        appointment2.setLocal("Sala 4 - Predio B");
        appointment2.setIdPaciente(1L);
        AppointmentEntity appointment3 = new AppointmentEntity();
        appointment3.setIdAgendamento(2L);
        appointment3.setTitulo("Consulta H");
        appointment3.setDescricao("Doutora Ana Carolina");
        appointment3.setData(LocalDateTime.now());
        appointment3.setLocal("Sala 3 - Predio B");
        appointment3.setIdPaciente(1L);
        return List.of(appointment1, appointment2, appointment3);
    }

    public static AppointmentRequestDTO appointmentRequestMock() {
        AppointmentRequestDTO appointment = new AppointmentRequestDTO();
        appointment.setTitulo("Consulta E");
        appointment.setDescricao("Doutor Carlos Miguel");
        appointment.setData("01/01/2024 16:00");
        appointment.setLocal("Sala 3 - Predio A");
        appointment.setNomeCompletoPaciente("João Silva Costa");
        return appointment;
    }

    public static List<AppointmentResponseDTO> appointmentListResponseMock() {
        List<AppointmentResponseDTO> listDTO = new ArrayList<AppointmentResponseDTO>();
        var appointmentListEntity = appointmentListEntityOfSameUserMock();
        appointmentListEntity.forEach(entity -> {
            listDTO.add(AppointmentMapper.toDTO(entity));
        });
        return listDTO;
    }
}
