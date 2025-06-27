package org.codelab.graacc.Agendamentos.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.codelab.graacc.Agendamentos.dto.request.PatientRequestDTO;
import org.codelab.graacc.Agendamentos.dto.response.PatientResponseDTO;
import org.codelab.graacc.Agendamentos.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@Tag(name = "Gerenciamento dos Pacientes")
public class PatientController {
    @Autowired
    PatientService service;

    @Operation(summary = "Registrar um novo Paciente.",
               description = "Obrigatório uso de token de autenticação Administrador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente registrado com sucesso.")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<PatientResponseDTO> addPatient(@Parameter(description = "Dados do novo Paciente", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PatientRequestDTO.class))})
                                                  @RequestBody PatientRequestDTO patientRequest) {
        PatientResponseDTO paciente = service.addPatient(patientRequest);
        return (paciente == null)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok().body(paciente);
    }

    @Operation(summary = "Obter lista com todos Pacientes.",
               description = "Obrigatório uso de token de autenticação Administrador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pacientes encontrados com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PatientResponseDTO.class))})
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<PatientResponseDTO>> listAllPatients() {
        var patientsList = service.findAllPatients();
        return (patientsList.isEmpty())
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok().body(patientsList);
    }

    @Operation(summary = "Editar dados do Paciente.",
            description = "Obrigatório uso de token de autenticação Administrador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente editado com sucesso.")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    ResponseEntity<PatientResponseDTO> editPatient(@PathVariable("id") Long idPaciente,
                                                   @RequestBody PatientRequestDTO patientRequest) {
        var patient = service.editPatient(patientRequest, idPaciente);
        return (patient == null)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok().body(patient);
    }

    @Operation(summary = "Deletar dados do Paciente.",
                description = "Obrigatório uso de token de autenticação Administrador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente deletado com sucesso.")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity deletePatient(@Parameter(description = "ID do Paciente", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))})
                                 @PathVariable("id") Long idPaciente) {
        service.deletePatient(idPaciente);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Pesquisar paciente por nome.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pacientes encontrado com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PatientResponseDTO.class))}),
            @ApiResponse(responseCode = "204", description = "Pacientes nao encontrado.")
    })
    @PostMapping("/pesquisar")
    ResponseEntity<PatientResponseDTO> findPatientByName(@RequestBody PatientRequestDTO patientRequest) {
        var patient = service.findPatientByName(patientRequest.nome());
        return (patient == null)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok().body(patient);
    }

}
