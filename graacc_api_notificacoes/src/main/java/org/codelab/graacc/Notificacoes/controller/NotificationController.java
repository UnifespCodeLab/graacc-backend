package org.codelab.graacc.Notificacoes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.codelab.graacc.Notificacoes.dto.AppointmentInfoDTO;
import org.codelab.graacc.Notificacoes.dto.NotificationDTO;
import org.codelab.graacc.Notificacoes.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacoes")
@Tag(name = "Notificações")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @Operation(summary = "Registrar novas notificações a partir do Agendamento.",
            description = "Obrigatório token de autenticação Admin.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificações registradas com sucesso.")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<List<NotificationDTO>> addNotifications(@RequestBody AppointmentInfoDTO appointmentInfo) {
        List<NotificationDTO> notifications = notificationService.createNotifications(appointmentInfo);
        return (notifications != null)
                ? new ResponseEntity<>(notifications, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Registrar novas notificações a partir de um conjunto de Agendamentos.",
            description = "Obrigatório token de autenticação Admin.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificações registradas com sucesso.")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/conjunto")
    public ResponseEntity<List<NotificationDTO>> addNotificationsOfListAppointments(@RequestBody List<AppointmentInfoDTO> listAppointmentInfo) {
        List<NotificationDTO> notifications = notificationService.createNotificationsMultipleAppointments(listAppointmentInfo);
        return (notifications != null)
                ? new ResponseEntity<>(notifications, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Obter notificações a partir de um id do Agendamento.",
            description = "Obrigatório token de autenticação Admin ou User.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificações listadas com sucesso."),
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{idAgendamento}")
    public ResponseEntity<List<NotificationDTO>> listaAllNotificationsUnread(@PathVariable("idAgendamento") long idAgendamento) {
        List<NotificationDTO> notifications = notificationService.getAllNotifications(idAgendamento);
        return (notifications != null)
                ? new ResponseEntity<>(notifications, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // lista notificacoes ativas com data maior que atual e que nao foram lidas ainda
    @Operation(summary = "Obter notificações não lidas e válidas a partir da data atual, por meio do id do Agendamento.",
            description = "Obrigatório token de autenticação Admin ou User.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificações listadas com sucesso."),
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/naoLidas")
    public ResponseEntity<List<NotificationDTO>> listaAllNotificationsUnreadWithFutureDates(@RequestBody List<AppointmentInfoDTO> listAppointmentInfo) {
        List<NotificationDTO> notifications = notificationService.getAllNotificationsUnreadWithFutureDates(listAppointmentInfo);
        return (notifications != null)
                ? new ResponseEntity<>(notifications, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Seta notificação como lida a partir do id da Notificação.",
            description = "Obrigatório token de autenticação Admin ou User.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificações marcada como lida com sucesso."),
            @ApiResponse(responseCode = "204", description = "Sem conteúdo.")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/{idNotificacao}/lida")
    public ResponseEntity<NotificationDTO> markNotificationAsRead(@PathVariable("idNotificacao") long idNotificacao) {
        NotificationDTO notification = notificationService.markAsReadNotification(idNotificacao);
        return (notification != null)
                ? new ResponseEntity<>(notification, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Deletar notificação a partir do id da Notificação.",
            description = "Obrigatório token de autenticação Admin ou User.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificações deletada com sucesso.")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idAgendamento}")
    public ResponseEntity deleteAllNotificationOfAppointment(@PathVariable("idAgendamento") long idAgendamento) {
        notificationService.deleteAllNotificationOfAppointment(idAgendamento);
        return ResponseEntity.ok().build();
    }
}
