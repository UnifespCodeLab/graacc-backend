package org.codelab.graacc.Agendamentos.mock;

import org.codelab.graacc.Agendamentos.dto.request.PatientEditRequestDTO;
import org.codelab.graacc.Agendamentos.dto.request.PatientRequestDTO;
import org.codelab.graacc.Agendamentos.dto.response.PatientResponseDTO;
import org.codelab.graacc.Agendamentos.entity.PatientEntity;

import java.util.List;

public class PatientMock {

    public static PatientResponseDTO patientExistedMock() {
        return new PatientResponseDTO(1, "João Silva Costa");
    }

    public static PatientEntity patientEntityMock() {
        PatientEntity entity = new PatientEntity();
        entity.setIdPaciente(1L);
        entity.setNome("João Silva Costa");
        return entity;
    }

    public static PatientRequestDTO patientRequestMock() {
        return new PatientRequestDTO("João Silva Costa");
    }

    public static PatientEditRequestDTO patientEditRequestMock() {
        return new PatientEditRequestDTO(1L, "João Carlos Silva Costa");
    }

    public static List<PatientEntity> patientEntityList() {
        PatientEntity entityA = new PatientEntity();
        entityA.setIdPaciente(1L);
        entityA.setNome("João Silva Costa");
        PatientEntity entityB = new PatientEntity();
        entityB.setIdPaciente(1L);
        entityB.setNome("João Silva Costa");
        return List.of(entityA, entityB);
    }
}
