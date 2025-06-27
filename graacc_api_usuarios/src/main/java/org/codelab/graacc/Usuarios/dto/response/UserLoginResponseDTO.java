package org.codelab.graacc.Usuarios.dto.response;

public class UserLoginResponseDTO {
    private String nome;
    private String token;

    public UserLoginResponseDTO(String nome, String token) {
        this.nome = nome;
        this.token = token;
    }

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
