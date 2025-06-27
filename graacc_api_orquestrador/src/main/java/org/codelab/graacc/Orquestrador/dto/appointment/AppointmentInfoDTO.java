package org.codelab.graacc.Orquestrador.dto.appointment;

public class AppointmentInfoDTO {
    private long idAgendamento;
    private String dataAgendamento;

    public long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public String getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(String dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }
}
