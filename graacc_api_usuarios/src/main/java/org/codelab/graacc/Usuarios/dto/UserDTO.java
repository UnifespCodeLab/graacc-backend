package org.codelab.graacc.Usuarios.dto;
import java.util.Arrays;

public class UserDTO {
    private Long idUsuario;
    private String nome;
    private String email;
    private boolean cadastroConfirmado;
    private String role;
    private Long idPaciente;
    private byte[] profileImage;

    public UserDTO() {
    }

    public UserDTO(Long idUsuario, String nome, String email, boolean cadastroConfirmado, String role, Long idPaciente) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.cadastroConfirmado = cadastroConfirmado;
        this.role = role;
        this.idPaciente = idPaciente;
    }

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

    public byte[] getProfileImage() {
      return Arrays.copyOf(profileImage, profileImage.length);
    }

    public void setProfileImage(byte[] profileImage) {
      this.profileImage = Arrays.copyOf(profileImage, profileImage.length);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cadastroConfirmado=" + cadastroConfirmado +
                ", role=" + role +
                ", idPaciente=" + idPaciente +
                '}';
    }
}
