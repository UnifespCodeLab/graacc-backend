package org.codelab.graacc.Agendamentos.controller;


import org.codelab.graacc.Agendamentos.dto.request.PatientEditRequestDTO;
import org.codelab.graacc.Agendamentos.dto.request.PatientRequestDTO;
import org.codelab.graacc.Agendamentos.mock.PatientMock;
import org.codelab.graacc.Agendamentos.service.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PatientControllerTest {
    @InjectMocks
    PatientController controller;

    @Mock
    PatientService serviceMock;

    @Test
    @DisplayName("Adiciona paciente com sucesso")
    void addPatientSuccess() {
        Mockito
                .when(serviceMock.addPatient(Mockito.any()))
                .thenReturn(PatientMock.patientExistedMock());
        var response = controller.addPatient(Mockito.any());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1, response.getBody().getIdPaciente());
        Assertions.assertEquals("João Silva Costa", response.getBody().getNome());
    }

    @Test
    @DisplayName("Adiciona paciente com erro")
    void addPatientReturnsNoContent() {
        Mockito
                .when(serviceMock.addPatient(Mockito.any()))
                .thenReturn(null);
        var response = controller.addPatient(PatientMock.patientRequestMock());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Lista todos pacientes com sucesso")
    void listAllPatientsSuccess() {
        Mockito
                .when(serviceMock.findAllPatients())
                .thenReturn(List.of(PatientMock.patientExistedMock()));
        var response = controller.listAllPatients();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1, response.getBody().size());
    }

    @Test
    @DisplayName("Lista todos pacientes com sucesso e retorna lista vazia")
    void listAllPatientsSuccessReturnsEmpty() {
        Mockito
                .when(serviceMock.findAllPatients())
                .thenReturn(new ArrayList<>());
        var response = controller.listAllPatients();
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Edita paciente com sucesso")
    void editPatientSuccess() {
        var responseMock = PatientMock.patientExistedMock();
        responseMock.setNome("João Carlos Silva Costa");
        Mockito
                .when(serviceMock.editPatient(Mockito.any(), Mockito.anyLong()))
                .thenReturn(responseMock);
        var response = controller.editPatient(1L, PatientMock.patientRequestMock());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1, response.getBody().getIdPaciente());
        Assertions.assertEquals("João Carlos Silva Costa", response.getBody().getNome());
    }

    @Test
    @DisplayName("Edita paciente com erro e retorna sem conteudo")
    void editPatientReturnsNoContent() {
        Mockito
                .when(serviceMock.editPatient(Mockito.any(), Mockito.anyLong()))
                .thenReturn(null);
        var response = controller.editPatient(10L, new PatientRequestDTO("Ana Paula Silva"));
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Deleta paciente com sucesso")
    void testDeletePatientSuccess() {
        Mockito.doNothing()
                .when(serviceMock).deletePatient(Mockito.any());
        var response = controller.deletePatient(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Pesquisa paciente com sucesso")
    void testFindPatientByNameSuccessfully() {
        Mockito
                .when(serviceMock.findPatientByName(Mockito.anyString()))
                .thenReturn(PatientMock.patientExistedMock());
        var resposta = controller.findPatientByName(PatientMock.patientRequestMock());
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals(1, resposta.getBody().getIdPaciente());
        Assertions.assertEquals("João Silva Costa", resposta.getBody().getNome());
    }

    @Test
    @DisplayName("Pesquisa paciente e retorna vazio")
    void testFindPatientByNameNoContent() {
        Mockito
                .when(serviceMock.findPatientByName(Mockito.anyString()))
                .thenReturn(null);
        var resposta = controller.findPatientByName(new PatientRequestDTO("Joana Maria Santana"));
        Assertions.assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
        Assertions.assertNull(resposta.getBody());
    }

}
