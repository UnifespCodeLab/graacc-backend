package org.codelab.graacc.Orquestrador.dto.user;

public class UserUpdateRequest {
    private String nome;
    private String email;
    private String nomeCompletoPaciente;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompletoPaciente() {
        return nomeCompletoPaciente;
    }

    public void setNomeCompletoPaciente(String nomeCompletoPaciente) {
        this.nomeCompletoPaciente = nomeCompletoPaciente;
    }
}
