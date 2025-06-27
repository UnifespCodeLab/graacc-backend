package org.codelab.graacc.Usuarios.mocks;

import org.codelab.graacc.Usuarios.dto.PatientDTO;
import org.codelab.graacc.Usuarios.dto.request.PatientRequestDTO;

import java.util.List;

public final class PatientMock {

    public static PatientRequestDTO createPatientRequestDTO() {
        return new PatientRequestDTO("Luiz Rogerio Silva");
    }

    public static PatientDTO createPatientExisted() {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setIdPaciente(2);
        patientDTO.setNome("Maria Oliveira Santos");
        return patientDTO;
    }

    public static List<PatientDTO> createListOfPatient() {
        PatientDTO patient1 = createPatientExisted();
        PatientDTO patient2 = new PatientDTO();
        patient2.setIdPaciente(1);
        patient2.setNome("João Silva Costa");
        return List.of(patient1, patient2);
    }

    public static List<PatientDTO> createListOfPatientDTO() {
        PatientDTO patient1 = new PatientDTO(1, "João Silva Costa");
        PatientDTO patient2 = new PatientDTO(2, "Maria Oliveira Santos");
        return List.of(patient1, patient2);
    }

    public static PatientDTO createPatientDTO() {
        return new PatientDTO(1, "João Silva Costa");
    }

}
