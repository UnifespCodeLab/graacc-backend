package org.codelab.graacc.Agendamentos.service;

import org.codelab.graacc.Agendamentos.dto.request.PatientRequestDTO;
import org.codelab.graacc.Agendamentos.entity.PatientEntity;
import org.codelab.graacc.Agendamentos.mock.PatientMock;
import org.codelab.graacc.Agendamentos.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
@DisplayName("PatientServiceTest")
public class PatientServiceTest {

    @InjectMocks
    PatientService service;

    @Mock
    PatientRepository repositoryMock;

    @Test
    @DisplayName("Adiciona paciente com sucesso")
    void testAddPatientSuccessfully() {
        Mockito
                .when(repositoryMock.findByNome(Mockito.anyString()))
                .thenReturn(null);
        Mockito
                .when(repositoryMock.save(Mockito.any(PatientEntity.class)))
                .thenReturn(new PatientEntity());
        PatientRequestDTO requisicao = PatientMock.patientRequestMock();
        var response = service.addPatient(requisicao);
        System.out.println(response);
    }

    @Test
    @DisplayName("Adiciona paciente ja existente e lanca excecao")
    void testAddPatientWithError() {
        Mockito
                .when(repositoryMock.findByNome(Mockito.anyString()))
                .thenReturn(PatientMock.patientEntityMock());
        PatientRequestDTO requisicao = new PatientRequestDTO("Maria Oliveira Santos");
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> service.addPatient(requisicao)
        );
    }

    @Test
    @DisplayName("Lista todos pacientes")
    void testFindAllPatientsSuccessfully() {
        Mockito
                .when(repositoryMock.findAll())
                .thenReturn(PatientMock.patientEntityList());
        var lista = service.findAllPatients();
        Assertions.assertNotNull(lista);
        Assertions.assertEquals(2, lista.size());
    }

    @Test
    @DisplayName("Lista todos pacientes e retorna lista vazia.")
    void testFindAllPatientsReturnEmptyList() {
        Mockito
                .when(repositoryMock.findAll())
                .thenReturn(new ArrayList<>());
        var lista = service.findAllPatients();
        Assertions.assertTrue(lista.isEmpty());
    }


    @Test
    @DisplayName("Edita paciente com sucesso.")
    void testEditPatientSuccessfully() {
        Mockito
                .when(repositoryMock.findByIdPaciente(Mockito.any()))
                .thenReturn(PatientMock.patientEntityMock());
        Mockito
                .when(repositoryMock.save(Mockito.any()))
                .thenReturn(PatientMock.patientEntityMock());
        var paciente = service.editPatient(PatientMock.patientRequestMock(), 1L );
        System.out.println(paciente);
        Assertions.assertEquals(1L, paciente.getIdPaciente());
        Assertions.assertEquals("João Silva Costa", paciente.getNome());
    }

    @Test
    @DisplayName("Edita paciente com erro.")
    void testEditPatientWithError() {
        Mockito
                .when(repositoryMock.findByIdPaciente(Mockito.any()))
                .thenReturn(null);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> service.editPatient(PatientMock.patientRequestMock(), 1L)
        );
    }

    @Test
    @DisplayName("Deleta paciente com sucesso.")
    void testDeletePatientSuccessfully() {
        Mockito
                .when(repositoryMock.findByIdPaciente(Mockito.any()))
                .thenReturn(PatientMock.patientEntityMock());
        Mockito
                .doNothing()
                .when(repositoryMock)
                .delete(PatientMock.patientEntityMock());

        Assertions.assertDoesNotThrow(
                () -> service.deletePatient(1L)
        );
    }

    @Test
    @DisplayName("Deleta paciente com erro.")
    void testDeletePatientWithError() {
        Mockito
                .when(repositoryMock.findByIdPaciente(Mockito.any()))
                .thenReturn(null);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> service.deletePatient(3L)
        );
    }

    @Test
    @DisplayName("Pesquisa paciente por nome com sucesso")
    void testFindPatientByNameSuccessfully() {
        Mockito
                .when(repositoryMock.findByNome(Mockito.any()))
                .thenReturn(PatientMock.patientEntityMock());
        var resposta = service.findPatientByName("Maria Oliveira Santos");
        Assertions.assertEquals(1L, resposta.getIdPaciente());
        Assertions.assertEquals("João Silva Costa", resposta.getNome());
    }

    @Test
    @DisplayName("Pesquisa paciente por nome e retorna nullo")
    void testFindPatientByNameReturnNull() {
        Mockito
                .when(repositoryMock.findByNome(Mockito.any()))
                .thenReturn(null);
        var resposta = service.findPatientByName("João Santos");
        Assertions.assertNull(resposta);
    }
}
