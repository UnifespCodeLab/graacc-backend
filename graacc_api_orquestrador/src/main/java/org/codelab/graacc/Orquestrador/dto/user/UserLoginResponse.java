package org.codelab.graacc.Orquestrador.dto.user;

public class UserLoginResponse {
    private String nome;
    private String token;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
