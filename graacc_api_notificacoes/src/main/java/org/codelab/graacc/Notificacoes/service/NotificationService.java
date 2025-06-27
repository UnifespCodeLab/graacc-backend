package org.codelab.graacc.Notificacoes.service;


import jakarta.transaction.Transactional;
import org.codelab.graacc.Notificacoes.dto.AppointmentInfoDTO;
import org.codelab.graacc.Notificacoes.dto.NotificationDTO;
import org.codelab.graacc.Notificacoes.entity.NotificationEntity;
import org.codelab.graacc.Notificacoes.mapper.NotificationMapper;
import org.codelab.graacc.Notificacoes.repository.NotificationRepository;
import org.codelab.graacc.Notificacoes.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Transactional
    public List<NotificationDTO> createNotifications(AppointmentInfoDTO appointmentInfo) {
        List<NotificationEntity> notificacoes = new ArrayList<>();
        List<LocalDateTime> datasNotificacoes = generateNotificationDates(appointmentInfo.getDataAgendamento());
        for (LocalDateTime data : datasNotificacoes) {
            NotificationEntity notificationEntity = new NotificationEntity();
            notificationEntity.setIdAgendamento(appointmentInfo.getIdAgendamento());
            notificationEntity.setData(data);
            notificationEntity.setLida(false);
            notificacoes.add(notificationEntity);
        }

        try {
            notificationRepository.saveAll(notificacoes);
            return notificacoes
                    .stream()
                    .map(entity -> NotificationMapper.toDTO(entity))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir Notificacoes.");
        }
    }

    public List<NotificationDTO> createNotificationsMultipleAppointments(List<AppointmentInfoDTO> listAppointmentInfo) {
        List<NotificationDTO> notificacoes = new ArrayList<>();
        listAppointmentInfo.stream().forEach(appointment -> notificacoes.addAll(createNotifications(appointment)));
        return notificacoes;
    }

    public NotificationDTO markAsReadNotification(long notificationId) {
        NotificationEntity entity = notificationRepository.findById(notificationId).orElse(null);
        if (entity == null) {
            return null;
        }
        entity.setLida(true);
        notificationRepository.save(entity);
        return NotificationMapper.toDTO(entity);
    }

    public List<NotificationDTO> getAllNotifications(Long idAgendamento) {
        List<NotificationEntity> notificationEntities = notificationRepository.findByIdAgendamento(idAgendamento);
        return notificationEntities
                .stream()
                .map(entity -> NotificationMapper.toDTO(entity))
                .collect(Collectors.toList());
    }

    public List<NotificationDTO> getAllNotificationsUnreadWithFutureDates(List<AppointmentInfoDTO> listAppointmentInfo) {
        List<NotificationDTO> notificacoes = new ArrayList<>();
        List<NotificationEntity> notificationEntities = new ArrayList<>();

        for(AppointmentInfoDTO appointmentInfo : listAppointmentInfo) {
            notificationEntities.addAll(notificationRepository
                    .findByIdAgendamentoAndLidaFalseAndDataAfter(appointmentInfo.getIdAgendamento(), LocalDateTime.now()));
        }

        notificacoes = notificationEntities
                .stream()
                .map(entity -> NotificationMapper.toDTO(entity))
                .collect(Collectors.toList());
        return notificacoes;
    }

    @Transactional
    public void deleteAllNotificationOfAppointment(long idAgendamento) {
        try {
            notificationRepository.deleteAllByIdAgendamento(idAgendamento);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao deletar Notificacoes.");
        }
    }

    private List<LocalDateTime> generateNotificationDates(String dataAgendamentoStr) {
        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime dataAgendamento = DateUtil.converterStringToData(dataAgendamentoStr);
        LocalDateTime umaSemanaAntes = dataAgendamento.minusWeeks(1);
        LocalDateTime tresDiasAntes = dataAgendamento.minusDays(3);
        LocalDateTime umDiaAntes = dataAgendamento.minusDays(1);
        LocalDateTime quatroHorasAntes = dataAgendamento.minusHours(4);

        return List.of(umaSemanaAntes, tresDiasAntes, umDiaAntes, quatroHorasAntes)
                .stream()
                .filter(data -> data.isAfter(dataAtual))
                .collect(Collectors.toList());
    }

}
