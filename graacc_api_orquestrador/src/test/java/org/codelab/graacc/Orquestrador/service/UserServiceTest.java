package org.codelab.graacc.Orquestrador.service;

import org.codelab.graacc.Orquestrador.integration.AppointmentClient;
import org.codelab.graacc.Orquestrador.integration.NotificationClient;
import org.codelab.graacc.Orquestrador.mocks.AppointmentMock;
import org.codelab.graacc.Orquestrador.mocks.NotificationMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Mock
    AppointmentClient appointmentClientMock;

    @Mock
    NotificationClient notificationClientMock;

    @InjectMocks
    UserService userService;

    String headerAutorizacao = "exemplo-token";

    @Test
    @DisplayName("Listar todos agendamentos com sucesso")
    void testListarTodosAgendamentosSucesso() {
        Mockito.when(appointmentClientMock.obterListaAgendamentosDoUsuario(Mockito.anyString()))
                .thenReturn(AppointmentMock.appointmentListDTOMock());
        var resposta = userService.listarTodosAgendamentos(headerAutorizacao);
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Listar agendamento especifico com sucesso")
    void testListarAgendamentoEspecificoSucesso() {
        Mockito.when(appointmentClientMock.listarAgendamentoEspecifico(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(AppointmentMock.appointmentDTOMock());
        var resposta = userService.listarAgendamentoEspecifico(headerAutorizacao, 1L);
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Listar todas notificacoes com sucesso")
    void testListarTodasNotificacoesSucesso() {
        Mockito.when(appointmentClientMock.obterListaAgendamentosDoUsuario(Mockito.anyString()))
                .thenReturn(AppointmentMock.appointmentListDTOMock());
        Mockito.when(notificationClientMock.obterListaNotificacoesNaoLidasDoUsuario(Mockito.anyString(), Mockito.any()))
                .thenReturn(NotificationMock.listNotificationDTOMock());

        var resposta = userService.listarTodasNotificacoes(headerAutorizacao);
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Listar todas notificacoes de um agendamento com sucesso")
    void testListarNotificacoesDeUmAgendamentoSucesso() {
        Mockito.when(notificationClientMock.obterListaNotificacoesDeUmAgendamentoEspecifico(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(NotificationMock.listNotificationDTOMock());

        var resposta = userService.listarNotificacoesDeUmAgendamento(headerAutorizacao, 1L);
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Marcar notificacao como lida com sucesso")
    void testMarcarNotificacaoLidaSucesso() {
        Mockito.when(notificationClientMock.marcarNotificacaoComoLida(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(NotificationMock.notificationDTOMock());

        var resposta = userService.marcarNotificacaoComoLida(headerAutorizacao, 1L);
        Assertions.assertNotNull(resposta);
    }
}
