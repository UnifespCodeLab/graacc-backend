package org.codelab.graacc.Orquestrador.controller;

import feign.FeignException;
import org.codelab.graacc.Orquestrador.dto.patient.PatientRequest;
import org.codelab.graacc.Orquestrador.integration.AppointmentClient;
import org.codelab.graacc.Orquestrador.mocks.AppointmentMock;
import org.codelab.graacc.Orquestrador.mocks.AppointmentNotificationResponseMock;
import org.codelab.graacc.Orquestrador.mocks.PatientMock;
import org.codelab.graacc.Orquestrador.service.AdminService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest
public class AdminControllerTest {
    @Mock
    private AdminService adminServiceMock;
    @Mock
    private AppointmentClient appointmentClientMock;

    @InjectMocks
    AdminController adminController;

    private String token = "token-exemplo";

    @Test
    @DisplayName("Adicionar agendamento com sucesso")
    void adicionarAgendamentoComSucesso() {
        Mockito.when(adminServiceMock.adicionarAgendamento(Mockito.anyString(), Mockito.any()))
                .thenReturn(AppointmentNotificationResponseMock.appointmentNotificationResponseMock());
        var resposta = adminController.adicionarAgendamento(token, AppointmentMock.appointmentRequestMock());
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Adicionar conjunto Agendamentos com sucesso")
    void adicionarConjuntoAgendamentosComSucesso() {
        Mockito.when(adminServiceMock.adicionarConjuntoAgendamentos(Mockito.anyString(), Mockito.any()))
                .thenReturn(AppointmentNotificationResponseMock.listAppointmentNotificationResponseMock());
        var resposta = adminController.adicionarConjuntoAgendamentos(token, List.of(AppointmentMock.appointmentRequestMock()));
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Listar Agendamento especifico com sucesso")
    void listarAgendamentoEspecificoComSucesso() {
        Mockito.when(appointmentClientMock.listarAgendamentoEspecifico(Mockito.anyString(), Mockito.any()))
                .thenReturn(AppointmentMock.appointmentDTOMock());
        var resposta = adminController.listarAgendamentoEspecifico(token, 1L);
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Listar todos Agendamentos com sucesso")
    void listarTodosAgendamentosComSucesso() {
        Mockito.when(appointmentClientMock.listarTodosAgendamentos(Mockito.anyString()))
                .thenReturn(AppointmentMock.appointmentListDTOMock());
        var resposta = adminController.listarTodosAgendamentos(token);
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Editar Agendamento com sucesso")
    void editarAgendamentoComSucesso() {
        Mockito.when(appointmentClientMock.editarAgendamento(Mockito.anyString(), Mockito.anyLong(), Mockito.any()))
                .thenReturn(AppointmentMock.appointmentDTOMock());
        var resposta = adminController.editarAgendamento(token, 1L, AppointmentMock.appointmentRequestMock());
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Deletar Agendamento com sucesso")
    void deletarAgendamentoComSucesso() {
        Mockito.when(appointmentClientMock.deletarAgendamento(Mockito.anyString(), Mockito.anyLong()))
                .thenReturn(ResponseEntity.ok().build());
        var resposta = adminController.deletarAgendamento(token, 1L);
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Adicionar Paciente com sucesso")
    void adicionarPacienteComSucesso() {
        Mockito.when(appointmentClientMock.adicionarPaciente(Mockito.anyString(), Mockito.any()))
                .thenReturn(PatientMock.patientDTOMock());
        var resposta = adminController.adicionarPaciente(token, PatientMock.patientRequestMock());
        Assertions.assertNotNull(resposta);
        Assertions.assertEquals(PatientMock.patientDTOMock().getIdPaciente(), resposta.getBody().getIdPaciente());
        Assertions.assertEquals(PatientMock.patientDTOMock().getNome(), resposta.getBody().getNome());
    }

    @Test
    @DisplayName("Listar Paciente especifico com sucesso")
    void pesquisarPacientePorNomeComSucesso() {
        Mockito.when(appointmentClientMock.pesquisarPacientePorNome(Mockito.anyString(), Mockito.any()))
                .thenReturn(PatientMock.patientDTOMock());
        PatientRequest request = new PatientRequest();
        var resposta = adminController.pesquisarPacientePorNome(token, request);
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Listar todos Pacientes com sucesso")
    void listarTodosPacientesComSucesso() {
        Mockito.when(appointmentClientMock.listarTodosPacientes(Mockito.anyString()))
                .thenReturn(List.of(PatientMock.patientDTOMock()));
        var resposta = adminController.listarTodosPacientes(token);
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Editar Paciente com sucesso")
    void editarPacienteComSucesso() {
        Mockito.when(appointmentClientMock.editarPaciente(Mockito.anyString(), Mockito.anyLong(), Mockito.any()))
                .thenReturn(PatientMock.patientDTOMock());
        var resposta = adminController.editarPaciente(token, 1L, PatientMock.patientRequestMock());
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Deletar Paciente com sucesso")
    void deletarPacienteComSucesso() {
        Mockito.when(appointmentClientMock.deletarAgendamento(Mockito.anyString(), Mockito.any()))
                .thenReturn(ResponseEntity.ok().build());
        var resposta = adminController.deletarPaciente(token, 1L);
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Testar lidar com excecoes FeignClient com sucesso")
    void testHandleFeignErrorsWithContentSucessoStatus400() {
        FeignException feignException = Mockito.mock(FeignException.class);
        Mockito.when(feignException.contentUTF8()).thenReturn("Erro específico do serviço externo");
        Mockito.when(feignException.status()).thenReturn(400);

        ResponseEntity<String> response = adminController.handleFeignErrors(feignException);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("Erro específico do serviço externo", response.getBody());
    }

    @Test
    @DisplayName("Testar lidar com excecoes FeignClient com sucesso")
    void testHandleFeignErrorsWithContentSucessoStatus500() {
        FeignException feignException = Mockito.mock(FeignException.class);
        Mockito.when(feignException.contentUTF8()).thenReturn("");
        Mockito.when(feignException.status()).thenReturn(500);

        ResponseEntity<String> response = adminController.handleFeignErrors(feignException);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("Erro ao comunicar com serviço externo.", response.getBody());
    }

    @Test
    @DisplayName("Testar lidar com excecoes FeignClient com sucesso - IllegalException")
    void testHandleFeignErrorsWithContentSucessoIllegalArgumentException() {
        FeignException feignException = Mockito.mock(FeignException.class);
        Mockito.when(feignException.contentUTF8()).thenThrow(IllegalArgumentException.class);

        ResponseEntity<String> response = adminController.handleFeignErrors(feignException);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("Erro no processamento da solicitação.", response.getBody());
    }

}
