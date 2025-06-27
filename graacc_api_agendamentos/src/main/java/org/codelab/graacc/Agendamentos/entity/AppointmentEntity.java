package org.codelab.graacc.Agendamentos.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Agendamento")
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgendamento;
    private String titulo;
    private String descricao;
    private LocalDateTime data;
    private String local;
    private Long idPaciente;

    public AppointmentEntity() {
    }


    public Long getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Long id_agendamento) {
        this.idAgendamento = id_agendamento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long id_paciente) {
        this.idPaciente = id_paciente;
    }
}
