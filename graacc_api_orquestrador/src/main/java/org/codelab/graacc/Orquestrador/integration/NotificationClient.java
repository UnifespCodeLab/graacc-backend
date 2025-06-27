package org.codelab.graacc.Orquestrador.integration;

import org.codelab.graacc.Orquestrador.dto.appointment.AppointmentInfoDTO;
import org.codelab.graacc.Orquestrador.dto.notification.NotificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "graacc-ms-notificacoes",
        url =  "${url.ms.notificacoes}"
)
public interface NotificationClient {

    @PostMapping("/notificacoes/naoLidas")
    List<NotificationDTO> obterListaNotificacoesNaoLidasDoUsuario(@RequestHeader("Authorization") String token,
                                                                  @RequestBody List<AppointmentInfoDTO> appointments);

    @GetMapping("/notificacoes/{idAgendamento}")
    List<NotificationDTO> obterListaNotificacoesDeUmAgendamentoEspecifico(@RequestHeader("Authorization") String token,
                                                                          @PathVariable("idAgendamento") Long idAgendamento);

    @PostMapping("/notificacoes")
    List<NotificationDTO> adicionarNotificacoes(@RequestHeader("Authorization") String token,
                                                @RequestBody AppointmentInfoDTO appointmentInfoDTO);

    @PostMapping("/notificacoes/conjunto")
    List<NotificationDTO> adicionarNotificacoesConjunto(@RequestHeader("Authorization") String token,
                                                        @RequestBody List<AppointmentInfoDTO> appointmentInfoList);
    @PostMapping("/notificacoes/{id}/lida")
    NotificationDTO marcarNotificacaoComoLida(@RequestHeader("Authorization") String token,
                                              @PathVariable("id") Long idNotificacao);
}
