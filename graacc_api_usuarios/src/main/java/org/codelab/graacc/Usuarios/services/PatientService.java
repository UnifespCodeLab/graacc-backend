package org.codelab.graacc.Usuarios.services;

import org.codelab.graacc.Usuarios.dto.PatientDTO;
import org.codelab.graacc.Usuarios.dto.request.PatientRequestDTO;
import org.codelab.graacc.Usuarios.integration.PatientClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    @Autowired
    private PatientClient patientClient;

    public PatientDTO findPatientByName(String name) {
        PatientDTO patient = patientClient.pesquisar(new PatientRequestDTO(name));
        if (patient != null) {
            return new PatientDTO(patient.getIdPaciente(), patient.getNome());
        } else {
            return null;
        }
    }

}
