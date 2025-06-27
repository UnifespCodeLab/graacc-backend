package org.codelab.graacc.Orquestrador.mocks;

import org.codelab.graacc.Orquestrador.dto.appointment.AppointmentNotificationResponse;

import java.util.List;

public class AppointmentNotificationResponseMock {

    public static AppointmentNotificationResponse appointmentNotificationResponseMock() {
        AppointmentNotificationResponse response = new AppointmentNotificationResponse();
        response.setAgendamento(AppointmentMock.appointmentDTOMock());
        response.setNotificacoes(NotificationMock.listNotificationDTOMock());
        return response;
    }

    public static List<AppointmentNotificationResponse> listAppointmentNotificationResponseMock() {
        AppointmentNotificationResponse response = new AppointmentNotificationResponse();
        response.setAgendamento(AppointmentMock.appointmentDTOMock());
        response.setNotificacoes(NotificationMock.listNotificationDTOMock());
        return List.of(response);
    }
}
