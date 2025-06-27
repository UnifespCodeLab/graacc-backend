package org.codelab.graacc.Orquestrador.controller;

import feign.FeignException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.codelab.graacc.Orquestrador.dto.appointment.AppointmentDTO;
import org.codelab.graacc.Orquestrador.dto.appointment.AppointmentNotificationResponse;
import org.codelab.graacc.Orquestrador.dto.appointment.AppointmentRequest;
import org.codelab.graacc.Orquestrador.dto.patient.PatientDTO;
import org.codelab.graacc.Orquestrador.dto.patient.PatientRequest;
import org.codelab.graacc.Orquestrador.integration.AppointmentClient;
import org.codelab.graacc.Orquestrador.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/graacc/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    AppointmentClient appointmentClient;

    @Operation(summary = "Adicionar Agendamento.", description = "É obrigatório uso de token de autenticação ADMIN.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Agendamento adicionado com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentNotificationResponse.class))})})
    @PostMapping("/agendamentos/adicionar")
    public ResponseEntity<AppointmentNotificationResponse> adicionarAgendamento(@RequestHeader("Authorization") String token,
                                                                                @RequestBody AppointmentRequest request) {
        var resposta = adminService.adicionarAgendamento(token, request);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Adicionar Conjunto de Agendamentos.", description = "É obrigatório uso de token de autenticação ADMIN.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Agendamentos adicionados com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentNotificationResponse.class))})})
    @PostMapping("/agendamentos/adicionar/conjunto")
    public ResponseEntity<List<AppointmentNotificationResponse>> adicionarConjuntoAgendamentos(@RequestHeader("Authorization") String token,
                                                                                        @RequestBody List<AppointmentRequest> request) {
        var resposta = adminService.adicionarConjuntoAgendamentos(token, request);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Listar Agendamento especifico.", description = "É obrigatório uso de token de autenticação ADMIN.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Agendamento específico listado com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentDTO.class))})})
    @GetMapping("/agendamentos/listar/{id}")
    public ResponseEntity<AppointmentDTO> listarAgendamentoEspecifico(@RequestHeader("Authorization") String token,
                                                            @PathVariable("id") Long idAgendamento) {
        System.out.println("Listar agendamento de id " + idAgendamento);
        var resposta = appointmentClient.listarAgendamentoEspecifico(token, idAgendamento);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Listar todos Agendamentos.", description = "É obrigatório uso de token de autenticação ADMIN.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Agendamentos listados com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentDTO.class))})})
    @GetMapping("/agendamentos/listar/todos")
    public ResponseEntity<List<AppointmentDTO>> listarTodosAgendamentos(@RequestHeader("Authorization") String token) {
        var resposta = appointmentClient.listarTodosAgendamentos(token);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Editar Agendamento especifico.", description = "É obrigatório uso de token de autenticação ADMIN.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Agendamento específico editado com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentDTO.class))})})
    @PutMapping("/agendamentos/editar/{id}")
    public ResponseEntity<AppointmentDTO> editarAgendamento(@RequestHeader("Authorization") String token,
                                                            @PathVariable("id") Long idAgendamento,
                                                            @RequestBody AppointmentRequest request) {
        var resposta = appointmentClient.editarAgendamento(token, idAgendamento, request);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Deletar Agendamento especifico.", description = "É obrigatório uso de token de autenticação ADMIN.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Agendamento específico deletado com sucesso.")})
    @DeleteMapping("/agendamentos/deletar/{id}")
    public ResponseEntity deletarAgendamento(@RequestHeader("Authorization") String token,
                                                            @PathVariable("id") Long idAgendamento) {
        appointmentClient.deletarAgendamento(token, idAgendamento);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Adicionar Paciente.", description = "É obrigatório uso de token de autenticação ADMIN.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Paciente adicionado com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))})})
    @PostMapping("/pacientes/adicionar")
    public ResponseEntity<PatientDTO> adicionarPaciente(@RequestHeader("Authorization") String token,
                                                                                @RequestBody PatientRequest request) {
        var resposta = appointmentClient.adicionarPaciente(token, request);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Pesquisar Paciente por nome.", description = "É obrigatório uso de token de autenticação ADMIN.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Paciente específico pesquisado com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))})})
    @PostMapping("/pacientes/pesquisar")
    public ResponseEntity<PatientDTO> pesquisarPacientePorNome(@RequestHeader("Authorization") String token,
                                                               @RequestBody PatientRequest patientRequest) {
        var resposta = appointmentClient.pesquisarPacientePorNome(token, patientRequest);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Listar Pacientes.", description = "É obrigatório uso de token de autenticação ADMIN.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Pacientes listados com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))})})
    @GetMapping("/pacientes/listar/todos")
    public ResponseEntity<List<PatientDTO>> listarTodosPacientes(@RequestHeader("Authorization") String token) {
        var resposta = appointmentClient.listarTodosPacientes(token);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Editar Paciente específico.", description = "É obrigatório uso de token de autenticação ADMIN.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Pacientes específico editado com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PatientDTO.class))})})
    @PutMapping("/pacientes/editar/{id}")
    public ResponseEntity<PatientDTO> editarPaciente(@RequestHeader("Authorization") String token,
                                                            @PathVariable("id") Long idPaciente,
                                                            @RequestBody PatientRequest request) {
        var resposta = appointmentClient.editarPaciente(token, idPaciente, request);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Deletar Paciente específico.", description = "É obrigatório uso de token de autenticação ADMIN.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Pacientes específico deletado com sucesso.")})
    @DeleteMapping("/pacientes/deletar/{id}")
    public ResponseEntity deletarPaciente(@RequestHeader("Authorization") String token,
                                                      @PathVariable("id") Long idPaciente) {
        appointmentClient.deletarPaciente(token, idPaciente);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignErrors(FeignException feignException) {
        feignException.printStackTrace();
        String mensagem;
        try {
            mensagem = feignException.contentUTF8();
            if (mensagem == null || mensagem.isBlank()) {
                mensagem = "Erro ao comunicar com serviço externo.";
            }
            return ResponseEntity.status(feignException.status()).body(mensagem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro no processamento da solicitação.");
        }
    }
}
