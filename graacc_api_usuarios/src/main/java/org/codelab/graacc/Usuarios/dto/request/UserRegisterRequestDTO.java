package org.codelab.graacc.Usuarios.dto.request;

public class UserRegisterRequestDTO {
    private String nome;
    private String email;
    private String senha;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeCompletoPaciente() {
        return nomeCompletoPaciente;
    }

    public void setNomeCompletoPaciente(String nomeCompletoPaciente) {
        this.nomeCompletoPaciente = nomeCompletoPaciente;
    }
}
