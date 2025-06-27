package org.codelab.graacc.Notificacoes.mapper;

import org.codelab.graacc.Notificacoes.dto.NotificationDTO;
import org.codelab.graacc.Notificacoes.entity.NotificationEntity;
import org.codelab.graacc.Notificacoes.util.DateUtil;

public class NotificationMapper {
    private NotificationMapper() {}

    public static NotificationDTO toDTO(NotificationEntity entity) {
        NotificationDTO dto = new NotificationDTO();
        dto.setIdNotificacao(entity.getIdNotificacao());
        dto.setData(DateUtil.formatarDataToString(entity.getData()));
        dto.setLida(entity.isLida());
        dto.setIdAgendamento(entity.getIdAgendamento());
        return dto;
    }
}
