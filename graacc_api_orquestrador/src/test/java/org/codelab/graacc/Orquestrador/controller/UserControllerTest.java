package org.codelab.graacc.Orquestrador.controller;

import feign.FeignException;
import org.codelab.graacc.Orquestrador.mocks.AppointmentMock;
import org.codelab.graacc.Orquestrador.mocks.NotificationMock;
import org.codelab.graacc.Orquestrador.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class UserControllerTest {
    @Mock
    UserService userServiceMock;
    @InjectMocks
    UserController userController;

    private String token = "token-exemplo";

    @Test
    @DisplayName("Obter todos agendamentos com sucesso")
    void obterTodosAgendamentosComSucesso() {
        Mockito.when(userServiceMock.listarTodosAgendamentos(Mockito.anyString()))
                .thenReturn(AppointmentMock.appointmentListDTOMock());
        var resposta = userController.obterTodosAgendamentos(token);
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Obter Agendamento especifico com sucesso")
    void obterAgendamentoEspecificoComSucesso() {
        Mockito.when(userServiceMock.listarAgendamentoEspecifico(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(AppointmentMock.appointmentDTOMock());
        var resposta = userController.obterAgendamentoEspecifico(token, 1L);
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Obter todas notificacoes com sucesso")
    void obterTodasNotificacoesComSucesso() {
        Mockito.when(userServiceMock.listarTodasNotificacoes(Mockito.anyString()))
                .thenReturn(NotificationMock.listNotificationDTOMock());
        var resposta = userController.obterTodasNotificacoes(token);
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Obter Notificacoes de um Agendamento especifico com sucesso")
    void obterNotificacoesDeUmAgendamentoEspecificoComSucesso() {
        Mockito.when(userServiceMock.listarNotificacoesDeUmAgendamento(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(NotificationMock.listNotificationDTOMock());
        var resposta = userController.obterTodasNotificacoesDeUmAgendamento(token, 1L);
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Marcar notificacao como lida com sucesso")
    void marcarNotificacaoComoLidaComSucesso() {
        Mockito.when(userServiceMock.marcarNotificacaoComoLida(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(NotificationMock.notificationDTOMock());
        var resposta = userController.marcarNotificacaoComoLida(token, 1L);
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Testar lidar com excecoes FeignClient com sucesso")
    void testHandleFeignErrorsWithContentSucessoStatus400() {
        FeignException feignException = Mockito.mock(FeignException.class);
        Mockito.when(feignException.contentUTF8()).thenReturn("Erro específico do serviço externo");
        Mockito.when(feignException.status()).thenReturn(400);

        ResponseEntity<String> response = userController.handleFeignErrors(feignException);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("Erro específico do serviço externo", response.getBody());
    }

    @Test
    @DisplayName("Testar lidar com excecoes FeignClient com sucesso")
    void testHandleFeignErrorsWithContentSucessoStatus500() {
        FeignException feignException = Mockito.mock(FeignException.class);
        Mockito.when(feignException.contentUTF8()).thenReturn("");
        Mockito.when(feignException.status()).thenReturn(500);

        ResponseEntity<String> response = userController.handleFeignErrors(feignException);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("Erro ao comunicar com serviço externo.", response.getBody());
    }

    @Test
    @DisplayName("Testar lidar com excecoes FeignClient com sucesso - IllegalException")
    void testHandleFeignErrorsWithContentSucessoIllegalArgumentException() {
        FeignException feignException = Mockito.mock(FeignException.class);
        Mockito.when(feignException.contentUTF8()).thenThrow(IllegalArgumentException.class);

        ResponseEntity<String> response = userController.handleFeignErrors(feignException);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("Erro no processamento da solicitação.", response.getBody());
    }
}
