package org.codelab.graacc.Notificacoes.mocks;


import org.codelab.graacc.Notificacoes.dto.NotificationDTO;
import org.codelab.graacc.Notificacoes.entity.NotificationEntity;
import org.codelab.graacc.Notificacoes.mapper.NotificationMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationMock {

    public static NotificationEntity notificationEntityMock() {
        NotificationEntity entity = new NotificationEntity();
        entity.setIdNotificacao(1);
        entity.setData(LocalDateTime.parse("2025-12-13T09:10"));
        entity.setLida(false);
        entity.setIdAgendamento(1);
        return entity;
    }

    public static NotificationEntity notificationEntityReadMock() {
        NotificationEntity entity = new NotificationEntity();
        entity.setIdNotificacao(1);
        entity.setData(LocalDateTime.parse("2025-12-13T09:10"));
        entity.setLida(true);
        entity.setIdAgendamento(1);
        return entity;
    }

    public static List<NotificationEntity> listNotificationEntityMock() {
        List<NotificationEntity> notificationEntities = new ArrayList<>();
        NotificationEntity entity1 = new NotificationEntity();
        entity1.setIdNotificacao(1);
        entity1.setData(LocalDateTime.parse("2025-12-13T09:10"));
        entity1.setLida(false);
        entity1.setIdAgendamento(1);
        notificationEntities.add(entity1);
        NotificationEntity entity2 = new NotificationEntity();
        entity2.setIdNotificacao(2);
        entity2.setData(LocalDateTime.parse("2025-12-17T09:10"));
        entity2.setLida(false);
        entity2.setIdAgendamento(1);
        notificationEntities.add(entity1);
        notificationEntities.add(entity2);
        return notificationEntities;
    }

    public static NotificationDTO notificationDTOMock() {
        NotificationEntity entity = notificationEntityMock();
        return NotificationMapper.toDTO(entity);
    }

    public static List<NotificationDTO> listNotificationDTOMock() {
        List<NotificationEntity> notificationEntities = listNotificationEntityMock();
        List<NotificationDTO> notificationDTOs = new ArrayList<>();
        for (NotificationEntity entity : notificationEntities) {
            NotificationDTO dto = NotificationMapper.toDTO(entity);
            notificationDTOs.add(dto);
        }
        return notificationDTOs;
    }


}