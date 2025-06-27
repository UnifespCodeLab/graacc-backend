package org.codelab.graacc.Agendamentos.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.codelab.graacc.Agendamentos.dto.response.AppointmentResponseDTO;
import org.codelab.graacc.Agendamentos.service.AppointmentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/agendamentos/usuario")
@Tag(name = "Listagem dos Agendamentos na visao dos Usuarios")
public class AppointmentUserController {
    @Autowired
    AppointmentUserService service;

    @GetMapping()
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<List<AppointmentResponseDTO>> getAppointment() {
        List<AppointmentResponseDTO> allAppointmentsOfUser = service.getAllAppointmentsOfUser();
        return (allAppointmentsOfUser.isEmpty())
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok().body(allAppointmentsOfUser);
    }
}
