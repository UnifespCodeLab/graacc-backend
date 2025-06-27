package org.codelab.graacc.Orquestrador.dto.appointment;

import org.codelab.graacc.Orquestrador.dto.notification.NotificationDTO;

import java.util.List;

public class AppointmentNotificationResponse {
    private AppointmentDTO agendamento;
    private List<NotificationDTO> notificacoes;

    public AppointmentNotificationResponse() {
    }

    public AppointmentNotificationResponse(AppointmentDTO agendamento, List<NotificationDTO> notificacoes) {
        this.agendamento = agendamento;
        this.notificacoes = notificacoes;
    }

    public AppointmentDTO getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(AppointmentDTO agendamento) {
        this.agendamento = agendamento;
    }

    public List<NotificationDTO> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<NotificationDTO> notificacoes) {
        this.notificacoes = notificacoes;
    }
}
