package org.codelab.graacc.Notificacoes.repository;

import org.codelab.graacc.Notificacoes.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    List<NotificationEntity> findByIdAgendamento(long idAgendamento);

    List<NotificationEntity> findByIdAgendamentoAndLidaFalseAndDataAfter(long idAgendamento, LocalDateTime dataAtual);

    void deleteAllByIdAgendamento(long idAgendamento);
}
