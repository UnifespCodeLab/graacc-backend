package org.codelab.graacc.Agendamentos.controller;


import org.codelab.graacc.Agendamentos.dto.request.AppointmentRequestDTO;
import org.codelab.graacc.Agendamentos.mock.AppointmentMock;
import org.codelab.graacc.Agendamentos.service.AppointmentService;
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
class AppointmentControllerTest {
    @InjectMocks
    AppointmentController controller;

    @Mock
    AppointmentService serviceMock;

    @Test
    @DisplayName("Adiciona Agendamento com sucesso")
    void testAddAppointmentSuccess() {
        Mockito
                .when(serviceMock.addAppointment(Mockito.any()))
                .thenReturn(AppointmentMock.appointmentResponseMock());
        var response = controller.addAppointment(AppointmentMock.appointmentRequestMock());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Adiciona Agendamento com erro")
    void testAddAppointmentError() {
        Mockito
                .when(serviceMock.addAppointment(Mockito.any(AppointmentRequestDTO.class)))
                .thenReturn(null);
        var response = controller.addAppointment(AppointmentMock.appointmentRequestMock());
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Lista todos Agendamentos com sucesso")
    void testGetAllAppointmentsSuccess() {
        Mockito
                .when(serviceMock.getAllAppointments())
                .thenReturn(AppointmentMock.appointmentListResponseMock());
        var response = controller.getAllAppointment();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(3, response.getBody().size());
    }

    @Test
    @DisplayName("Lista todos Agendamentos com sucesso e retorna sem conteudo")
    void testGetAllAppointmentsReturnsEmptyList() {
        Mockito
                .when(serviceMock.getAllAppointments())
                .thenReturn(new ArrayList<>());
        var response = controller.getAllAppointment();
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Lista Agendamento específico com sucesso")
    void testGetAppointmentSuccess() {
        Mockito
                .when(serviceMock.getAppointment(Mockito.any()))
                .thenReturn(AppointmentMock.appointmentResponseMock());
        var response = controller.getAppointment(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1L, response.getBody().getIdAgendamento());
    }

    @Test
    @DisplayName("Lista Agendamento específico com sucesso e retorna sem conteudo")
    void testGetAppointmentSuccessReturnsEmpty() {
        Mockito
                .when(serviceMock.getAppointment(Mockito.any()))
                .thenReturn(null);
        var response = controller.getAppointment(1L);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Edita Agendamento com sucesso")
    void testUpdateAppointmentSuccess() {
        Mockito
                .when(serviceMock.updateAppointment(Mockito.any(AppointmentRequestDTO.class), Mockito.anyLong()))
                .thenReturn(AppointmentMock.appointmentResponseMock());
        var response = controller.updateAppointment(AppointmentMock.appointmentRequestMock(),1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Edita Agendamento com erro")
    void testUpdateAppointmentSuccessReturnsEmpty() {
        Mockito
                .when(serviceMock.updateAppointment(Mockito.any(), Mockito.anyLong()))
                .thenReturn(null);
        var response = controller.updateAppointment(AppointmentMock.appointmentRequestMock(),1L);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Deleta Agendamento com sucesso")
    void testDeleteAppointmentSuccess() {
        Mockito.doNothing()
                .when(serviceMock).deleteAppointment(Mockito.anyLong());
        var response = controller.deleteAppointment(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
