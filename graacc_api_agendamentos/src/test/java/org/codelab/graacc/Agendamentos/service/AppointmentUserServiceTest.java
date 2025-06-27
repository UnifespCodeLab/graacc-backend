package org.codelab.graacc.Agendamentos.service;


import org.codelab.graacc.Agendamentos.mock.AppointmentMock;
import org.codelab.graacc.Agendamentos.mock.UserLoggedInfoMock;
import org.codelab.graacc.Agendamentos.repository.AppointmentRepository;
import org.codelab.graacc.Agendamentos.security.UserLoggedInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;

@SpringBootTest
public class AppointmentUserServiceTest {

    @InjectMocks
    AppointmentUserService service;

    @Mock
    AppointmentRepository repositoryAppointmentMock;

    private void securityMockSetup() {
        UserLoggedInfo userLogged = UserLoggedInfoMock.userLoggedInfoMock();
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getPrincipal()).thenReturn(userLogged);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    @DisplayName("Busca todos agendamentos do Usuario logado.")
    void testGetAllAppointmentsOfUserSuccessfully() {
        securityMockSetup();
        Mockito
                .when(repositoryAppointmentMock.findAllByIdPaciente(Mockito.anyLong()))
                .thenReturn(AppointmentMock.appointmentListEntityOfSameUserMock());
        var response = service.getAllAppointmentsOfUser();
        Assertions.assertEquals(3, response.size());
    }

    @Test
    @DisplayName("Busca todos agendamentos do Usuario logado e retorna lista vazia.")
    void testGetAllAppointmentsOfUserSuccessfullyReturnsEmptyList() {
        securityMockSetup();
        Mockito
                .when(repositoryAppointmentMock.findAllByIdPaciente(Mockito.anyLong()))
                .thenReturn(new ArrayList<>());
        var response = service.getAllAppointmentsOfUser();
        Assertions.assertTrue(response.isEmpty());
    }

    @Test
    @DisplayName("Busca todos agendamentos do Usuario logado e retorna excecao.")
    void testGetAllAppointmentsOfUserWithErrorThrowsException() {
        securityMockSetup();
        Mockito
                .when(repositoryAppointmentMock.findAllByIdPaciente(Mockito.anyLong()))
                .thenThrow(IllegalArgumentException.class);
        Assertions.assertThrows(
                RuntimeException.class,
                () -> service.getAllAppointmentsOfUser()
        );
    }
}
