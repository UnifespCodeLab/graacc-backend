package org.codelab.graacc.Notificacoes.controller;

import org.codelab.graacc.Notificacoes.mocks.AppointmentMock;
import org.codelab.graacc.Notificacoes.mocks.NotificationMock;
import org.codelab.graacc.Notificacoes.service.NotificationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
class NotificationControllerTest {

    @Mock
    NotificationService serviceMock;

    @InjectMocks
    NotificationController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Registra notificações com sucesso")
    void testAddNotificationsSuccess() {
        Mockito
                .when(serviceMock.createNotifications(Mockito.any()))
                .thenReturn(NotificationMock.listNotificationDTOMock());
        var response = controller.addNotifications(AppointmentMock.appointmentInfoMock());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1, response.getBody().get(0).getIdNotificacao());
        Assertions.assertNotNull(response.getBody().get(0).getData());
        Assertions.assertFalse(response.getBody().get(0).isLida());
        Assertions.assertEquals(1, response.getBody().get(0).getIdAgendamento());
    }

    @Test
    @DisplayName("Registra notificações com erro")
    void testAdNotificationsError() {
        Mockito
                .when(serviceMock.createNotifications(Mockito.any()))
                .thenReturn(null);
        var response = controller.addNotifications(AppointmentMock.appointmentInfoMock());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Registra notificações a partir de um conjunto de agendamentos com sucesso")
    void testAddNotificationsOfListAppointmentsSuccess() {
        Mockito
                .when(serviceMock.createNotifications(Mockito.any()))
                .thenReturn(NotificationMock.listNotificationDTOMock());
        var response = controller.addNotificationsOfListAppointments(AppointmentMock.appointmentInfoListMock());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Registra notificações a partir de um conjunto de agendamentos com erro")
    void testAddNotificationsOfListAppointmentsError() {
        Mockito
                .when(serviceMock.createNotifications(Mockito.any()))
                .thenReturn(null);
        var response = controller.addNotificationsOfListAppointments(AppointmentMock.appointmentInfoListMock());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Obter notificações a partir de um id do Agendamento com sucesso")
    void testListaAllNotificationsUnreadSuccess() {
        Mockito
                .when(serviceMock.getAllNotifications(Mockito.any()))
                .thenReturn(NotificationMock.listNotificationDTOMock());
        var response = controller.listaAllNotificationsUnread(AppointmentMock.appointmentInfoListMock().get(0).getIdAgendamento());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Obter notificações a partir de um id do Agendamento com erro")
    void testListaAllNotificationsUnreadError() {
        Mockito
                .when(serviceMock.getAllNotifications(Mockito.any()))
                .thenReturn(null);
        var response = controller.listaAllNotificationsUnread(AppointmentMock.appointmentInfoListMock().get(0).getIdAgendamento());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Obter notificações não lidas e válidas a partir da data atual com sucesso")
    void testListaAllNotificationsUnreadWithFutureDatesSuccess() {
        Mockito
                .when(serviceMock.getAllNotificationsUnreadWithFutureDates(Mockito.any()))
                .thenReturn(NotificationMock.listNotificationDTOMock());
        var response = controller.listaAllNotificationsUnreadWithFutureDates(AppointmentMock.appointmentInfoListMock());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Obter notificações não lidas e válidas a partir da data atual com erro")
    void testListaAllNotificationsUnreadWithFutureDatesError() {
        Mockito
                .when(serviceMock.getAllNotificationsUnreadWithFutureDates(Mockito.any()))
                .thenReturn(null);
        var response = controller.listaAllNotificationsUnreadWithFutureDates(AppointmentMock.appointmentInfoListMock());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Marca notificação como lida com sucesso")
    void testMarkNotificationAsReadSuccess() {
        Mockito
                .when(serviceMock.markAsReadNotification(Mockito.anyLong()))
                .thenReturn(NotificationMock.notificationDTOMock());
        var response = controller.markNotificationAsRead(AppointmentMock.appointmentInfoListMock().get(0).getIdAgendamento());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Marca notificação como lida com erro")
    void testMarkNotificationAsReadError() {
        Mockito
                .when(serviceMock.markAsReadNotification(Mockito.anyLong()))
                .thenReturn(null);
        var response = controller.markNotificationAsRead(AppointmentMock.appointmentInfoListMock().get(0).getIdAgendamento());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("Deletar notificação com sucesso")
    void testDeleteAllNotificationOfAppointmentSuccess() {
        Mockito
                .doNothing()
                .when(serviceMock)
                .deleteAllNotificationOfAppointment(Mockito.anyLong());
        var response = controller.deleteAllNotificationOfAppointment(AppointmentMock.appointmentInfoListMock().get(0).getIdAgendamento());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
