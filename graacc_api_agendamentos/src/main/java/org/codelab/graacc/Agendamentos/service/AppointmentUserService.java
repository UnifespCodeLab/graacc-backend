package org.codelab.graacc.Agendamentos.service;

import org.codelab.graacc.Agendamentos.dto.response.AppointmentResponseDTO;
import org.codelab.graacc.Agendamentos.entity.AppointmentEntity;
import org.codelab.graacc.Agendamentos.mapper.AppointmentMapper;
import org.codelab.graacc.Agendamentos.repository.AppointmentRepository;
import org.codelab.graacc.Agendamentos.security.UserLoggedInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentUserService {
    @Autowired
    AppointmentRepository appointmentRepository;

    public List<AppointmentResponseDTO> getAllAppointmentsOfUser() {
        UserLoggedInfo userInfo = getUserFromToken();
        List<AppointmentResponseDTO> listAppointments = new ArrayList<>();
        try {
            List<AppointmentEntity> listAppointmentsEntity =
                    appointmentRepository.findAllByIdPaciente(userInfo.getIdPaciente());
            listAppointmentsEntity.forEach(
                    entity -> listAppointments.add(AppointmentMapper.toDTO(entity))
            );
            return listAppointments;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar Lista de Agendamentos.");
        }
    }

    private UserLoggedInfo getUserFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserLoggedInfo user = (UserLoggedInfo) authentication.getPrincipal();
        if (user == null) throw new RuntimeException("Token inválido.");
        return user;
    }
}
