package org.codelab.graacc.Orquestrador.dto.patient;

public class PatientDTO {
    private long idPaciente;
    private String nome;

    public long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
