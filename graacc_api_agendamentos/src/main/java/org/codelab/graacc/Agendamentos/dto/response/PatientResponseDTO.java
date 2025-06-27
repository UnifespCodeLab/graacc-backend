package org.codelab.graacc.Agendamentos.dto.response;

public class PatientResponseDTO {
    private long idPaciente;
    private String nome;

    public PatientResponseDTO(long id, String nome) {
        this.idPaciente = id;
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
