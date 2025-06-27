package org.codelab.graacc.Orquestrador.mocks;

import org.codelab.graacc.Orquestrador.dto.patient.PatientDTO;
import org.codelab.graacc.Orquestrador.dto.patient.PatientRequest;

public class PatientMock {

    public static PatientRequest patientRequestMock() {
        PatientRequest patientRequest = new PatientRequest();
        patientRequest.setNome("João Silva Costa");
        return patientRequest;
    }

    public static PatientDTO patientDTOMock() {
        PatientDTO patient = new PatientDTO();
        patient.setNome("João Silva Costa");
        patient.setIdPaciente(1);
        return patient;
    }


}
