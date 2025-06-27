package org.codelab.graacc.Agendamentos.mapper;

import org.codelab.graacc.Agendamentos.dto.request.AppointmentRequestDTO;
import org.codelab.graacc.Agendamentos.dto.response.AppointmentResponseDTO;
import org.codelab.graacc.Agendamentos.entity.AppointmentEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppointmentMapper {
    private AppointmentMapper() {}

    public static AppointmentEntity toEntity(AppointmentRequestDTO dto, Long id_paciente) {
        AppointmentEntity entity = new AppointmentEntity();
        entity.setTitulo(dto.getTitulo());
        entity.setDescricao(dto.getDescricao());
        entity.setLocal(dto.getLocal());
        entity.setData(converterData(dto.getData()));
        entity.setIdPaciente(id_paciente);
        return entity;
    }

    public static AppointmentResponseDTO toDTO(AppointmentEntity entity) {
        AppointmentResponseDTO responseDTO = new AppointmentResponseDTO();
        responseDTO.setIdAgendamento(entity.getIdAgendamento());
        responseDTO.setTitulo(entity.getTitulo());
        responseDTO.setDescricao(entity.getDescricao());
        responseDTO.setLocal(entity.getLocal());
        responseDTO.setData(entity.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        responseDTO.setIdPaciente(entity.getIdPaciente());
        return responseDTO;
    }

    public static LocalDateTime converterData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            LocalDateTime dataFormatada = LocalDateTime.parse(data, formatter);
            return dataFormatada;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter Data do Agendamento - tente novamento no formato dd/MM/yyyy HH:mm");
        }
    }

}
