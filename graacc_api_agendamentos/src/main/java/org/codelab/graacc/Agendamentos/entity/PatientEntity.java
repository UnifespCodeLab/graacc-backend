package org.codelab.graacc.Agendamentos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Paciente")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPaciente;
    private String nome;

    public PatientEntity() {
    }

    public PatientEntity(String nome) {
        this.nome = nome;
    }

    public long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(long id_paciente) {
        this.idPaciente = id_paciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
