package org.codelab.graacc.Usuarios.integration;

import org.codelab.graacc.Usuarios.dto.PatientDTO;
import org.codelab.graacc.Usuarios.dto.request.PatientRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "api-graacc-agendamentos",
        url =  "${api.agendamentos.url}"
)
public interface PatientClient {

    @PostMapping("/pacientes/pesquisar")
    PatientDTO pesquisar(@RequestBody PatientRequestDTO patientRequestDTO);
}
