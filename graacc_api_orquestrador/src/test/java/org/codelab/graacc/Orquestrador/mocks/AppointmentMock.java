package org.codelab.graacc.Orquestrador.mocks;

import org.codelab.graacc.Orquestrador.dto.appointment.AppointmentDTO;
import org.codelab.graacc.Orquestrador.dto.appointment.AppointmentRequest;

import java.util.List;

public class AppointmentMock {

    public static AppointmentRequest appointmentRequestMock() {
        AppointmentRequest appointment = new AppointmentRequest();
        appointment.setTitulo("Consulta E");
        appointment.setDescricao("Doutor Carlos Miguel");
        appointment.setData("01/11/2025 16:00");
        appointment.setLocal("Sala 3 - Predio A");
        appointment.setNomeCompletoPaciente("João Silva Costa");
        return appointment;
    }

    public static AppointmentDTO appointmentDTOMock() {
        AppointmentDTO appointment = new AppointmentDTO();
        appointment.setIdAgendamento(1L);
        appointment.setTitulo("Consulta D");
        appointment.setDescricao("Doutor Carlos Miguel");
        appointment.setData("13/10/2025 16:00");
        appointment.setLocal("Sala 3 - Predio A");
        appointment.setIdPaciente(1L);
        return appointment;
    }

    public static List<AppointmentDTO> appointmentListDTOMock() {
        AppointmentDTO appointment1 = appointmentDTOMock();
        AppointmentDTO appointment2 = new AppointmentDTO();
        appointment2.setIdAgendamento(1L);
        appointment2.setTitulo("Consulta E");
        appointment2.setDescricao("Doutor Carlos Miguel");
        appointment2.setData("01/11/2025 16:00");
        appointment2.setLocal("Sala 3 - Predio A");
        appointment2.setIdPaciente(1L);
        return List.of(appointment1, appointment2);
    }
}
