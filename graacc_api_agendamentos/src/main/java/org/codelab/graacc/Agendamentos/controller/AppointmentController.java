package org.codelab.graacc.Agendamentos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.codelab.graacc.Agendamentos.dto.request.AppointmentRequestDTO;
import org.codelab.graacc.Agendamentos.dto.response.AppointmentResponseDTO;
import org.codelab.graacc.Agendamentos.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@Tag(name = "Gerenciamento dos Agendamentos")
public class AppointmentController {
    @Autowired
    AppointmentService service;

    @Operation(summary = "Registrar um novo Agendamento.",
            description = "Obrigatório uso de token de autenticação Administrador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento registrado com sucesso.")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<AppointmentResponseDTO> addAppointment(@Parameter(description = "Dados do novo Agendamento", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentRequestDTO.class))})
                                                          @RequestBody AppointmentRequestDTO request) {
        AppointmentResponseDTO addedAppointment = service.addAppointment(request);
        return (addedAppointment == null)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok().body(addedAppointment);
    }

    @Operation(summary = "Listar informações do Agendamento pelo ID.",
            description = "Obrigatório uso de token de autenticação Administrador ou Usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento listado com sucesso."),
            @ApiResponse(responseCode = "204", description = "Agendamento nao encontrado.")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    ResponseEntity<AppointmentResponseDTO> getAppointment(@Parameter(description = "ID do Agendamento", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))})
                                                          @PathVariable("id") Long id) {
        AppointmentResponseDTO findedAppointment = service.getAppointment(id);
        return (findedAppointment == null)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok().body(findedAppointment);
    }

    @Operation(summary = "Listar todos Agendamentos.",
            description = "Obrigatório uso de token de autenticação Administrador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamentos listado com sucesso."),
            @ApiResponse(responseCode = "204", description = "Agendamentos nao encontrado.")
    })
    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<AppointmentResponseDTO>> getAllAppointment() {
        List<AppointmentResponseDTO> listAppointments = service.getAllAppointments();
        return (listAppointments.isEmpty())
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok().body(listAppointments);
    }

    @Operation(summary = "Editar um Agendamento.",
            description = "Obrigatório uso de token de autenticação Administrador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento editado com sucesso.")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<AppointmentResponseDTO> updateAppointment(@Parameter(description = "Dados do Agendamento", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentRequestDTO.class))})
                                                             @RequestBody AppointmentRequestDTO request,
                                                             @Parameter(description = "ID do Agendamento", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))})
                                                             @PathVariable("id") Long id) {
        AppointmentResponseDTO updatedAppointment = service.updateAppointment(request, id);
        return (updatedAppointment == null)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok().body(updatedAppointment);
    }

    @Operation(summary = "Deletar um Agendamento.",
            description = "Obrigatório uso de token de autenticação Administrador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento deletado com sucesso.")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity deleteAppointment(@Parameter(description = "ID do Agendamento", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))})
                                                             @PathVariable("id") Long id) {
        service.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }
}
