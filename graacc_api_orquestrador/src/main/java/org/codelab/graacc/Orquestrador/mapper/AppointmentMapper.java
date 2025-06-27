package org.codelab.graacc.Orquestrador.mapper;

import org.codelab.graacc.Orquestrador.dto.appointment.AppointmentDTO;
import org.codelab.graacc.Orquestrador.dto.appointment.AppointmentInfoDTO;

import java.util.ArrayList;
import java.util.List;

public class AppointmentMapper {
    private AppointmentMapper() {}

    public static List<AppointmentInfoDTO> toAppointmentInfoList(List<AppointmentDTO> list) {
        List<AppointmentInfoDTO> infoList = new ArrayList<>();

        for (AppointmentDTO appointmentDTO : list) {
            AppointmentInfoDTO appointmentInfoDTO = new AppointmentInfoDTO();
            appointmentInfoDTO.setIdAgendamento(appointmentDTO.getIdAgendamento());
            appointmentInfoDTO.setDataAgendamento(appointmentDTO.getData());
            infoList.add(appointmentInfoDTO);
        }
        return infoList;
    }

    public static AppointmentInfoDTO toAppointmentInfo(AppointmentDTO dto) {
        AppointmentInfoDTO info = new AppointmentInfoDTO();
        info.setIdAgendamento(dto.getIdAgendamento());
        info.setDataAgendamento(dto.getData());
        return info;
    }
}
