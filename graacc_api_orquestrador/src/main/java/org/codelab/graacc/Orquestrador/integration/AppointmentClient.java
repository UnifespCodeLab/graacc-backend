package org.codelab.graacc.Orquestrador.integration;

import jakarta.websocket.server.PathParam;
import org.codelab.graacc.Orquestrador.dto.appointment.AppointmentDTO;
import org.codelab.graacc.Orquestrador.dto.appointment.AppointmentRequest;
import org.codelab.graacc.Orquestrador.dto.patient.PatientDTO;
import org.codelab.graacc.Orquestrador.dto.patient.PatientRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "graacc-ms-agendamentos",
        url =  "${url.ms.agendamentos}"
)
public interface AppointmentClient {

    /*
     * Gerenciamento de Agendamentos
     */
    @GetMapping("/agendamentos/usuario")
    List<AppointmentDTO> obterListaAgendamentosDoUsuario(@RequestHeader("Authorization") String token);

    @PostMapping("/agendamentos")
    AppointmentDTO adicionarAgendamento(@RequestHeader("Authorization") String token,
                                        @RequestBody AppointmentRequest appointmentRequest);

    @GetMapping("/agendamentos/{id}")
    AppointmentDTO listarAgendamentoEspecifico(@RequestHeader("Authorization") String token,
                                               @PathVariable("id") Long idAgendamento);

    @GetMapping("/agendamentos")
    List<AppointmentDTO> listarTodosAgendamentos(@RequestHeader("Authorization") String token);

    @PutMapping("/agendamentos/{id}")
    AppointmentDTO editarAgendamento(@RequestHeader("Authorization") String token,
                                     @PathVariable("id") Long idAgendamento,
                                     @RequestBody AppointmentRequest appointmentRequest);

    @DeleteMapping("/agendamentos/{id}")
    ResponseEntity deletarAgendamento(@RequestHeader("Authorization") String token,
                                      @PathVariable("id") Long idAgendamento);

    /*
     * Gerenciamento de Pacientes
     */
    @PostMapping("/pacientes")
    PatientDTO adicionarPaciente(@RequestHeader("Authorization") String token,
                                        @RequestBody PatientRequest patientRequest);

    @PostMapping("/pacientes/pesquisar")
    PatientDTO pesquisarPacientePorNome(@RequestHeader("Authorization") String token,
                                        @RequestBody PatientRequest patientRequest);

    @GetMapping("/pacientes")
    List<PatientDTO> listarTodosPacientes(@RequestHeader("Authorization") String token);

    @PutMapping("/pacientes/{id}")
    PatientDTO editarPaciente(@RequestHeader("Authorization") String token,
                                     @PathVariable("id") Long idPaciente,
                                     @RequestBody PatientRequest patientRequest);

    @DeleteMapping("/pacientes/{id}")
    ResponseEntity deletarPaciente(@RequestHeader("Authorization") String token,
                                   @PathVariable("id") Long idPaciente);
}
