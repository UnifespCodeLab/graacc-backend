package org.codelab.graacc.Orquestrador.dto.user;

public class UserDTO {
    private Long idUsuario;
    private String nome;
    private String email;
    private boolean cadastroConfirmado;
    private String role;
    private Long idPaciente;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

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

    public boolean isCadastroConfirmado() {
        return cadastroConfirmado;
    }

    public void setCadastroConfirmado(boolean cadastroConfirmado) {
        this.cadastroConfirmado = cadastroConfirmado;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }
}
