package org.codelab.graacc.Notificacoes.mocks;

import org.codelab.graacc.Notificacoes.dto.AppointmentInfoDTO;

import java.util.ArrayList;
import java.util.List;

public class AppointmentMock {

    public static AppointmentInfoDTO appointmentInfoMock() {
        AppointmentInfoDTO appointment = new AppointmentInfoDTO();
        appointment.setIdAgendamento(1);
        appointment.setDataAgendamento("30/12/2025 11:15");
        return appointment;
    }

    public static List<AppointmentInfoDTO> appointmentInfoListMock() {
        List<AppointmentInfoDTO> listAppointments = new ArrayList<>();
        AppointmentInfoDTO appointment1 = new AppointmentInfoDTO();
        appointment1.setIdAgendamento(1);
        appointment1.setDataAgendamento("30/12/2025 11:15");
        AppointmentInfoDTO appointment2 = new AppointmentInfoDTO();
        appointment2.setIdAgendamento(1);
        appointment2.setDataAgendamento("10/12/2025 14:00");
        listAppointments.add(appointment1);
        listAppointments.add(appointment2);
        return listAppointments;
    }
}
