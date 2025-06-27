package org.codelab.graacc.Agendamentos.service;

import jakarta.persistence.PersistenceException;
import org.codelab.graacc.Agendamentos.mock.AppointmentMock;
import org.codelab.graacc.Agendamentos.mock.PatientMock;
import org.codelab.graacc.Agendamentos.repository.AppointmentRepository;
import org.codelab.graacc.Agendamentos.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class AppointmentServiceTest {

    @InjectMocks
    AppointmentService service;

    @Mock
    AppointmentRepository repositoryAppointmentMock;

    @Mock
    PatientRepository repositoryPatientMock;

    @Test
    @DisplayName("Adiciona agendamento com sucesso")
    void testAddAppointmentSuccessfully() {
        Mockito
                .when(repositoryPatientMock.findByNome(Mockito.any()))
                .thenReturn(PatientMock.patientEntityMock());
        Mockito
                .when(repositoryAppointmentMock.save(Mockito.any()))
                .thenReturn(AppointmentMock.appointmentEntityMock());
        var resposta = service.addAppointment(AppointmentMock.appointmentRequestMock());
        Assertions.assertNotNull(resposta);
        Assertions.assertEquals(1L, resposta.getIdAgendamento());
        Assertions.assertEquals("Consulta D", resposta.getTitulo());
        Assertions.assertEquals("Doutor Carlos Miguel", resposta.getDescricao());
        Assertions.assertNotNull(resposta.getData());
        Assertions.assertEquals("Sala 3 - Predio A", resposta.getLocal());
        Assertions.assertEquals(1L, resposta.getIdPaciente());
    }

    @Test
    @DisplayName("Adiciona agendamento com erro e lanca excecao")
    void testAddAppointmentWithError() {
        Mockito
                .when(repositoryPatientMock.findByNome(Mockito.any()))
                .thenReturn(PatientMock.patientEntityMock());
        Mockito
                .when(repositoryAppointmentMock.save(Mockito.any()))
                .thenThrow(PersistenceException.class);
        Assertions.assertThrows(
                RuntimeException.class,
                () -> service.addAppointment(AppointmentMock.appointmentRequestMock())
        );
    }

    @Test
    @DisplayName("Atualiza agendamento com sucesso")
    void testUpdateAppointmentSuccessfully() {
        Mockito
                .when(repositoryAppointmentMock.findById(Mockito.any()))
                        .thenReturn(Optional.of(AppointmentMock.appointmentEntityMock()));
        Mockito
                .when(repositoryPatientMock.findByNome(Mockito.any()))
                .thenReturn(PatientMock.patientEntityMock());
        Mockito
                .when(repositoryAppointmentMock.save(Mockito.any()))
                .thenReturn(AppointmentMock.appointmentEntityMock());
        var request = AppointmentMock.appointmentRequestMock();
        var response = service.updateAppointment(request, 1L);
        Assertions.assertNotNull(response);
    }

    @Test
    @DisplayName("Atualiza agendamento com erro, lanca excecao.")
    void testUpdateAppointmentWithErrorThrowsException() {
        Mockito
                .when(repositoryAppointmentMock.findById(Mockito.anyLong()))
                .thenReturn(null);
        Mockito
                .when(repositoryPatientMock.findByNome(Mockito.any()))
                .thenReturn(PatientMock.patientEntityMock());
        Mockito
                .when(repositoryAppointmentMock.save(Mockito.any()))
                .thenThrow(PersistenceException.class);

        Assertions.assertThrows(
                RuntimeException.class,
                () -> service.updateAppointment(AppointmentMock.appointmentRequestMock(), 10L)
        );
    }

    @Test
    @DisplayName("Deleta agendamento com sucesso")
    void testDeleteAppointmentSuccessfully() {
        Mockito
                .when(repositoryAppointmentMock.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(AppointmentMock.appointmentEntityMock()));
        Mockito
                .doNothing()
                .when(repositoryPatientMock).deleteById(Mockito.any());
        Assertions.assertDoesNotThrow(
                () -> service.deleteAppointment(1L)
        );
    }

    @Test
    @DisplayName("Deleta agendamento com erro, lanca excecao pois Agendamento nao foi encontrado.")
    void testDeleteAppointmentWithError() {
        Mockito
                .when(repositoryAppointmentMock.findById(Mockito.anyLong()))
                .thenReturn(null);
        Assertions.assertThrows(
                RuntimeException.class,
                () -> service.deleteAppointment(10L)
        );
    }

    @Test
    @DisplayName("Obter agendamento com sucesso")
    void testGetAppointmentSuccessfully() {
        Mockito
                .when(repositoryAppointmentMock.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(AppointmentMock.appointmentEntityMock()));
        var response = service.getAppointment(1L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1L, response.getIdAgendamento());
    }

    @Test
    @DisplayName("Obter agendamento com erro")
    void testGetAppointmentWithError() {
        Mockito
                .when(repositoryAppointmentMock.findById(Mockito.anyLong()))
                .thenReturn(null);
        Assertions.assertThrows(
                Exception.class,
                () -> service.getAppointment(10L)
        );
    }

    @Test
    @DisplayName("Obter todos agendamentos com sucesso")
    void testGetAllAppointmentsSuccessfully() {
        Mockito
                .when(repositoryAppointmentMock.findAll())
                .thenReturn(AppointmentMock.appointmentListEntityMock());
        var response = service.getAllAppointments();
    }

    @Test
    @DisplayName("Obter todos agendamentos com erro, lanca excecao.")
    void testGetAllAppointmentsWithError() {
        Mockito
                .when(repositoryAppointmentMock.findAll())
                .thenThrow(PersistenceException.class);
        Assertions.assertThrows(
                RuntimeException.class,
                () -> service.getAllAppointments()
        );
    }


}
