package org.codelab.graacc.Agendamentos.controller;

import org.codelab.graacc.Agendamentos.mock.AppointmentMock;
import org.codelab.graacc.Agendamentos.service.AppointmentUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@SpringBootTest
public class AppointmentUserControllerTest {
    @InjectMocks
    AppointmentUserController controller;

    @Mock
    AppointmentUserService serviceMock;

    @Test
    @DisplayName("Lista todos agendamentos do Usuario com sucesso")
    void testGetAppointmentSuccess() {
        Mockito
                .when(serviceMock.getAllAppointmentsOfUser())
                .thenReturn(AppointmentMock.appointmentListResponseMock());
        var response = controller.getAppointment();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(3, response.getBody().size());
    }

    @Test
    @DisplayName("Lista todos agendamentos do Usuario com sucesso e retorna lista vazia")
    void testGetAppointmentSuccessReturnsEmptyList() {
        Mockito
                .when(serviceMock.getAllAppointmentsOfUser())
                .thenReturn(new ArrayList<>());
        var response = controller.getAppointment();
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
