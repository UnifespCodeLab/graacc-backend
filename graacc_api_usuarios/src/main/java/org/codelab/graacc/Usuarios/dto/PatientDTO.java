package org.codelab.graacc.Usuarios.dto;

public class PatientDTO {
    private long idPaciente;
    private String nome;

    public PatientDTO() {
    }

    public PatientDTO(long idPaciente, String nome) {
        this.idPaciente = idPaciente;
        this.nome = nome;
    }

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

    @Override
    public String toString() {
        return "PatientDTO{" +
                "id=" + idPaciente +
                ", nome='" + nome + '\'' +
                '}';
    }
}
