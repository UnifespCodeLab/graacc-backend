package org.codelab.graacc.Usuarios.services;

import org.codelab.graacc.Usuarios.dto.PatientDTO;
import org.codelab.graacc.Usuarios.integration.PatientClient;
import org.codelab.graacc.Usuarios.mocks.PatientMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("PatientServiceTest")
public class PatientServiceTest {
    @InjectMocks
    PatientService service;

    @Mock
    PatientClient patientClientMock;

    @Test
    @DisplayName("Pesquisa paciente por nome com sucesso")
    void testFindPatientByNameSuccessfully() {
        Mockito
                .when(patientClientMock.pesquisar(Mockito.any()))
                .thenReturn(PatientMock.createPatientExisted());
        PatientDTO resposta = service.findPatientByName("Maria Oliveira Santos");
        Assertions.assertEquals(2, resposta.getIdPaciente());
        Assertions.assertEquals("Maria Oliveira Santos", resposta.getNome());
    }

    @Test
    @DisplayName("Pesquisa paciente por nome e retorna nullo")
    void testFindPatientByNameReturnNull() {
        Mockito
                .when(patientClientMock.pesquisar(Mockito.any()))
                .thenReturn(null);
        PatientDTO resposta = service.findPatientByName("João Santos");
        Assertions.assertNull(resposta);
    }

}
