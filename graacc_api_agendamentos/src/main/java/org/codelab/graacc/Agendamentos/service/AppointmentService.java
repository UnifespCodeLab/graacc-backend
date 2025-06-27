package org.codelab.graacc.Agendamentos.service;

import jakarta.transaction.Transactional;
import org.codelab.graacc.Agendamentos.dto.request.AppointmentRequestDTO;
import org.codelab.graacc.Agendamentos.dto.response.AppointmentResponseDTO;
import org.codelab.graacc.Agendamentos.entity.AppointmentEntity;
import org.codelab.graacc.Agendamentos.entity.PatientEntity;
import org.codelab.graacc.Agendamentos.mapper.AppointmentMapper;
import org.codelab.graacc.Agendamentos.repository.AppointmentRepository;
import org.codelab.graacc.Agendamentos.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public AppointmentResponseDTO addAppointment(AppointmentRequestDTO request) {
        AppointmentResponseDTO addedAppointment = new AppointmentResponseDTO();
        PatientEntity patient = patientRepository.findByNome(request.getNomeCompletoPaciente());
        if (patient == null) {
            throw new RuntimeException("Erro ao inserir Agendamento - Paciente nao encontrado.");
        }
        try {
            AppointmentEntity entity = AppointmentMapper.toEntity(request, patient.getIdPaciente());
            entity = appointmentRepository.save(entity);
            return AppointmentMapper.toDTO(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inserir Agendamento.");
        }
    }

    @Transactional
    public AppointmentResponseDTO updateAppointment(AppointmentRequestDTO request, long id) {
        AppointmentResponseDTO appointment = getAppointment(id);
        if (appointment != null) {
            try {
                AppointmentEntity entity = appointmentRepository.findById(id).orElse(null);
                PatientEntity patient = patientRepository.findByNome(request.getNomeCompletoPaciente());
                if (entity != null) {
                    entity.setTitulo(request.getTitulo());
                    entity.setDescricao(request.getDescricao());
                    entity.setLocal(request.getLocal());
                    entity.setData(AppointmentMapper.converterData(request.getData()));
                    entity.setIdPaciente(patient.getIdPaciente());
                    entity = appointmentRepository.save(entity);
                    return AppointmentMapper.toDTO(entity);
                } else {
                    return null;
                }
            } catch (Exception e) {
                throw new RuntimeException("Erro ao editar Agendamento.");
            }
        }
        return null;
    }

    @Transactional
    public void deleteAppointment(Long id) {
        AppointmentResponseDTO appointment = getAppointment(id);
        if (appointment != null) {
            try {
                appointmentRepository.deleteById(id);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao deletar Agendamento.");
            }
        } else {
            throw new RuntimeException("Não foi possível deletar Agendamento, pois não foi encontrado nenhum Agendamento com esse id.");
        }
    }

    @Transactional
    public AppointmentResponseDTO getAppointment(Long id) {
        try {
            AppointmentEntity entity = appointmentRepository.findById(id).orElse(null);
            return AppointmentMapper.toDTO(entity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar Agendamento.");
        }
    }

    @Transactional
    public List<AppointmentResponseDTO> getAllAppointments() {
        List<AppointmentResponseDTO> listAppointments = new ArrayList<>();
        try {
            List<AppointmentEntity> listAppointmentsEntity = appointmentRepository.findAll();
            listAppointmentsEntity.forEach(
                    entity -> listAppointments.add(AppointmentMapper.toDTO(entity))
            );
            return listAppointments;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar Lista de Agendamentos.");
        }
    }
}
