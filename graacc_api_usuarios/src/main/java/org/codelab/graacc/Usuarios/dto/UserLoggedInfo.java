package org.codelab.graacc.Usuarios.dto;

public class UserLoggedInfo {
    private Long idUsuario;
    private Long idPaciente;
    private String email;
    private String role;

    public UserLoggedInfo() {
    }

    public UserLoggedInfo(Long idUsuario, Long idPaciente, String email, String role) {
        this.idUsuario = idUsuario;
        this.idPaciente = idPaciente;
        this.email = email;
        this.role = role;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserLoggedInfo{" +
                "idUsuario=" + idUsuario +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
