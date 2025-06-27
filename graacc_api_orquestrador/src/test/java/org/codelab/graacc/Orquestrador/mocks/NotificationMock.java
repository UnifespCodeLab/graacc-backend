package org.codelab.graacc.Orquestrador.mocks;

import org.codelab.graacc.Orquestrador.dto.notification.NotificationDTO;

import java.util.ArrayList;
import java.util.List;

public class NotificationMock {

    public static NotificationDTO notificationDTOMock() {
        NotificationDTO notification = new NotificationDTO();
        notification.setIdNotificacao(1);
        notification.setData("29/10/2025 16:00");
        notification.setLida(false);
        notification.setIdAgendamento(1);
        return notification;
    }
    
    public static List<NotificationDTO> listNotificationDTOMock() {
        List<NotificationDTO> notificationEntities = new ArrayList<>();
        NotificationDTO notification1 = new NotificationDTO();
        notification1.setIdNotificacao(1);
        notification1.setData("29/10/2025 16:00");
        notification1.setLida(false);
        notification1.setIdAgendamento(1);
        notificationEntities.add(notification1);
        NotificationDTO notification2 = new NotificationDTO();
        notification2.setIdNotificacao(2);
        notification2.setData("21/10/2025 16:00");
        notification2.setLida(false);
        notification2.setIdAgendamento(1);
        notificationEntities.add(notification1);
        notificationEntities.add(notification2);
        return notificationEntities;
    }
}
