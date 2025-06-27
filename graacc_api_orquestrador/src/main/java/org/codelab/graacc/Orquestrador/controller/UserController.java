package org.codelab.graacc.Orquestrador.controller;

import feign.FeignException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.codelab.graacc.Orquestrador.dto.appointment.AppointmentDTO;
import org.codelab.graacc.Orquestrador.dto.notification.NotificationDTO;
import org.codelab.graacc.Orquestrador.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/graacc/api/usuario")
public class UserController {
    @Autowired
    UserService userService;

    @Operation(summary = "Obter todos Agendamentos de um Usuário.", description = "Obrigatório uso de token de autenticação USER.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Agendamentos listados com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentDTO.class))})})
    @GetMapping("/agendamentos")
    ResponseEntity<List<AppointmentDTO>> obterTodosAgendamentos(@RequestHeader("Authorization") String token) {
        var resposta = userService.listarTodosAgendamentos(token);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Listar Agendamento especifico de um Usuário.", description = "Obrigatório uso de token de autenticação USER.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Agendamentos listado com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentDTO.class))})})
    @GetMapping("/agendamentos/{id}")
    ResponseEntity<AppointmentDTO> obterAgendamentoEspecifico(@RequestHeader("Authorization") String token,
                                                              @PathVariable("id") Long idAgendamento) {
        var resposta = userService.listarAgendamentoEspecifico(token, idAgendamento);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Obter todos Notificações de um Usuário.", description = "Obrigatório uso de token de autenticação USER.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Notificações listadas com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = NotificationDTO.class))})})
    @GetMapping("/notificacoes")
    ResponseEntity<List<NotificationDTO>> obterTodasNotificacoes(@RequestHeader("Authorization") String token) {
        var resposta = userService.listarTodasNotificacoes(token);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Obter todos Notificações de um Agendamento específico de um Usuário.", description = "Obrigatório uso de token de autenticação USER.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Notificações listados com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = NotificationDTO.class))})})
    @GetMapping("/notificacoes/agendamento/{id}")
    ResponseEntity<List<NotificationDTO>> obterTodasNotificacoesDeUmAgendamento(@RequestHeader("Authorization") String token,
                                                                                @PathVariable("id") Long idAgendamento) {
        var resposta = userService.listarNotificacoesDeUmAgendamento(token, idAgendamento);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Marca Notificação como Lida.", description = "Obrigatório uso de token de autenticação USER.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Notificações listados com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = NotificationDTO.class))})})
    @PostMapping("/notificacoes/lida/{id}")
    ResponseEntity<NotificationDTO> marcarNotificacaoComoLida(@RequestHeader("Authorization") String token,
                                                                                @PathVariable("id") Long idNotificacao) {
        var resposta = userService.marcarNotificacaoComoLida(token, idNotificacao);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignErrors(FeignException feignException) {
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
