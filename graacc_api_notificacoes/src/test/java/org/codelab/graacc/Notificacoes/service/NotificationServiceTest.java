package org.codelab.graacc.Notificacoes.service;

import jakarta.persistence.PersistenceException;
import org.codelab.graacc.Notificacoes.mocks.AppointmentMock;
import org.codelab.graacc.Notificacoes.mocks.NotificationMock;
import org.codelab.graacc.Notificacoes.repository.NotificationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class NotificationServiceTest {
    @Mock
    NotificationRepository repository;

    @InjectMocks
    NotificationService service;

    @Test
    @DisplayName("Adiciona notificacoes com sucesso")
    void testCreateNotificationsSuccess() {
        Mockito
                .when(repository.saveAll(Mockito.any()))
                .thenReturn(NotificationMock.listNotificationEntityMock());
        var resposta = service.createNotifications(AppointmentMock.appointmentInfoMock());
        Mockito.verify(repository).saveAll(Mockito.any());
        Assertions.assertNotNull(resposta);
        Assertions.assertEquals(4, resposta.size());
    }

    @Test
    @DisplayName("Adiciona notificacoes com erro e lanca excecao")
    void testCreateNotificationsError() {
        Mockito
                .when(repository.saveAll(Mockito.any()))
                .thenThrow(PersistenceException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> service.createNotifications(AppointmentMock.appointmentInfoMock())
        );
    }

    @Test
    @DisplayName("Adiciona notificacoes para multiplos agendamentos com sucesso")
    void testCreateNotificationsMultipleAppointmentsSuccess() {
        Mockito
                .when(repository.saveAll(Mockito.any()))
                .thenReturn(NotificationMock.listNotificationEntityMock());
        var resposta = service.createNotificationsMultipleAppointments(AppointmentMock.appointmentInfoListMock());
        Mockito.verify(repository, Mockito.times(2)).saveAll(Mockito.any());
        Assertions.assertNotNull(resposta);
        Assertions.assertEquals(8, resposta.size());
    }

    @Test
    @DisplayName("Marca notificacao como lida com sucesso")
    void testMarkAsReadNotificationSuccess() {
        Mockito
                .when(repository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(NotificationMock.notificationEntityMock()));
        Mockito.when(repository.save(Mockito.any()))
                .thenReturn(NotificationMock.notificationEntityReadMock());

        var resposta = service.markAsReadNotification(NotificationMock.notificationEntityMock().getIdAgendamento());

        Mockito.verify(repository).save(Mockito.any());
        Mockito.verify(repository).findById(Mockito.anyLong());
        Assertions.assertNotNull(resposta);
        Assertions.assertTrue(resposta.isLida());
    }


    @Test
    @DisplayName("Lista notificacoes com sucesso")
    void testGetAllNotificationsSuccess() {
        Mockito
                .when(repository.findByIdAgendamento(Mockito.anyLong()))
                .thenReturn(NotificationMock.listNotificationEntityMock());
        var resposta = service.getAllNotifications(AppointmentMock.appointmentInfoMock().getIdAgendamento());
        Assertions.assertNotNull(resposta);
        Assertions.assertEquals(3, resposta.size());
    }

    @Test
    @DisplayName("Lista notificacoes nao lidas e datas futuras com sucesso")
    void testGetAllNotificationsUnreadWithFutureDatesSuccess() {
        Mockito
                .when(repository.findByIdAgendamentoAndLidaFalseAndDataAfter(Mockito.anyLong(), Mockito.any()))
                .thenReturn(NotificationMock.listNotificationEntityMock());
        var resposta = service.getAllNotificationsUnreadWithFutureDates(AppointmentMock.appointmentInfoListMock());
        Assertions.assertNotNull(resposta);
        Assertions.assertEquals(6, resposta.size());
    }

    @Test
    @DisplayName("Deleta notificacao com sucesso")
    void testDeleteAllNotificationOfAppointmentSuccess() {
        Mockito.doNothing()
                .when(repository).deleteAllByIdAgendamento(Mockito.anyLong());

        Assertions.assertDoesNotThrow(
                () -> service.deleteAllNotificationOfAppointment(AppointmentMock.appointmentInfoMock().getIdAgendamento())
        );
        Mockito.verify(repository).deleteAllByIdAgendamento(Mockito.anyLong());
    }

    @Test
    @DisplayName("Deleta notificacao com erro")
    void testDeleteAllNotificationOfAppointmentError() {
        Mockito.doThrow(PersistenceException.class)
                .when(repository).deleteAllByIdAgendamento(Mockito.anyLong());

        Assertions.assertThrows(
                RuntimeException.class,
                () -> service.deleteAllNotificationOfAppointment(AppointmentMock.appointmentInfoMock().getIdAgendamento())
        );
    }
}
