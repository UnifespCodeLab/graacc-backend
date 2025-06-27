package org.codelab.graacc.Agendamentos.service;

import jakarta.transaction.Transactional;
import org.codelab.graacc.Agendamentos.dto.request.PatientRequestDTO;
import org.codelab.graacc.Agendamentos.dto.response.PatientResponseDTO;
import org.codelab.graacc.Agendamentos.entity.PatientEntity;
import org.codelab.graacc.Agendamentos.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepository repository;

    @Transactional
    public PatientResponseDTO addPatient(PatientRequestDTO patientRequest) {
        if (findPatientByName(patientRequest.nome()) != null) {
            throw new IllegalArgumentException("Paciente ja existe na base de dados.");
        }
        var entity = new PatientEntity(patientRequest.nome());
        try {
            PatientEntity pacienteSalvo = repository.save(entity);
            return new PatientResponseDTO(pacienteSalvo.getIdPaciente(), pacienteSalvo.getNome());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar paciente.", e);
        }
    }

    public PatientResponseDTO findPatientByName(String name) {
        var patient = repository.findByNome(name);
        if (patient != null) {
            return new PatientResponseDTO(patient.getIdPaciente(), patient.getNome());
        } else {
            return null;
        }
    }

    @Transactional
    public List<PatientResponseDTO> findAllPatients() {
        var entityList = repository.findAll();
        var dtoList = new ArrayList<PatientResponseDTO>();
        entityList.forEach(patientEntity -> dtoList.add(
                new PatientResponseDTO(
                        patientEntity.getIdPaciente(),
                        patientEntity.getNome())
        ));
        return dtoList;
    }

    @Transactional
    public PatientResponseDTO editPatient(PatientRequestDTO patientRequest, Long idPaciente) {
        PatientEntity entity = repository.findByIdPaciente(idPaciente);
        if (entity == null) {
            throw new IllegalArgumentException("Paciente inexistente com esse id na base de dados.");
        } else {
            entity.setNome(patientRequest.nome());
            repository.save(entity);
            return new PatientResponseDTO(idPaciente, entity.getNome());
        }
    }

    @Transactional
    public void deletePatient(Long idPaciente) {
        PatientEntity entity = repository.findByIdPaciente(idPaciente);
        if (entity == null) {
            throw new IllegalArgumentException("Paciente inexistente com esse id na base de dados.");
        } else {
            try {
                repository.delete(entity);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao deletar Paciente.");
            }
        }
    }
}

