package org.codelab.graacc.Orquestrador.service;

import org.codelab.graacc.Orquestrador.integration.AppointmentClient;
import org.codelab.graacc.Orquestrador.integration.NotificationClient;
import org.codelab.graacc.Orquestrador.mocks.AppointmentMock;
import org.codelab.graacc.Orquestrador.mocks.AppointmentNotificationResponseMock;
import org.codelab.graacc.Orquestrador.mocks.NotificationMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AdminServiceTest {
    @Mock
    AppointmentClient appointmentClientMock;
    @Mock
    NotificationClient notificationClientMock;
    @InjectMocks
    AdminService adminService;

    String token = "token-exemplo";

    @Test
    @DisplayName("Adicionar Agendamento com sucesso")
    void testAdicionarAgendamentoComSucesso() {
        Mockito.when(appointmentClientMock.adicionarAgendamento(Mockito.anyString(), Mockito.any()))
                .thenReturn(AppointmentMock.appointmentDTOMock());
        Mockito.when(notificationClientMock.adicionarNotificacoes(Mockito.anyString(), Mockito.any()))
                .thenReturn(NotificationMock.listNotificationDTOMock());
        var resposta = adminService.adicionarAgendamento(token, AppointmentMock.appointmentRequestMock());
        Assertions.assertNotNull(resposta);
        Assertions.assertEquals(AppointmentNotificationResponseMock.appointmentNotificationResponseMock().getAgendamento().getIdAgendamento(), resposta.getAgendamento().getIdAgendamento());
        Assertions.assertEquals(AppointmentNotificationResponseMock.appointmentNotificationResponseMock().getAgendamento().getData(), resposta.getAgendamento().getData());
        Assertions.assertEquals(AppointmentNotificationResponseMock.appointmentNotificationResponseMock().getAgendamento().getDescricao(), resposta.getAgendamento().getDescricao());
        Assertions.assertEquals(AppointmentNotificationResponseMock.appointmentNotificationResponseMock().getAgendamento().getLocal(), resposta.getAgendamento().getLocal());
        Assertions.assertEquals(AppointmentNotificationResponseMock.appointmentNotificationResponseMock().getAgendamento().getIdPaciente(), resposta.getAgendamento().getIdPaciente());
        Assertions.assertEquals(AppointmentNotificationResponseMock.appointmentNotificationResponseMock().getAgendamento().getTitulo(), resposta.getAgendamento().getTitulo());
        Assertions.assertEquals(AppointmentNotificationResponseMock.appointmentNotificationResponseMock().getNotificacoes().get(0).getData(), resposta.getNotificacoes().get(0).getData());
        Assertions.assertEquals(AppointmentNotificationResponseMock.appointmentNotificationResponseMock().getNotificacoes().get(0).getIdAgendamento(), resposta.getNotificacoes().get(0).getIdAgendamento());
        Assertions.assertEquals(AppointmentNotificationResponseMock.appointmentNotificationResponseMock().getNotificacoes().get(0).getIdNotificacao(), resposta.getNotificacoes().get(0).getIdNotificacao());
    }

    @Test
    @DisplayName("Adicionar Conjunto de Agendamentos com sucesso")
    void testAdicionarConjuntoAgendamentosComSucesso() {
        Mockito.when(appointmentClientMock.adicionarAgendamento(Mockito.anyString(), Mockito.any()))
                .thenReturn(AppointmentMock.appointmentDTOMock());
        Mockito.when(notificationClientMock.adicionarNotificacoes(Mockito.anyString(), Mockito.any()))
                .thenReturn(NotificationMock.listNotificationDTOMock());
        var resposta = adminService.adicionarConjuntoAgendamentos(token, List.of(AppointmentMock.appointmentRequestMock()));
        Assertions.assertNotNull(resposta);
    }
}
