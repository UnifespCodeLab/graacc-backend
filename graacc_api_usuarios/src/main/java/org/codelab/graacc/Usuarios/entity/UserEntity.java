package org.codelab.graacc.Usuarios.entity;

import java.util.Arrays;
import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private boolean cadastroConfirmado;

    @Column(nullable = false)
    private String role;

    private byte[] profileImage;

    private Long idPaciente;

    public UserEntity() {
    }

    public UserEntity(String nome, String email, String senha, boolean cadastroConfirmado, String role, Long idPaciente) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cadastroConfirmado = cadastroConfirmado;
        this.role = role;
        this.idPaciente = idPaciente;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long id_usuario) {
        this.idUsuario = id_usuario;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean getCadastroConfirmado() {
        return cadastroConfirmado;
    }

    public void setCadastroConfirmado(boolean cadastro_confirmado) {
        this.cadastroConfirmado = cadastro_confirmado;
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

    public void setIdPaciente(Long paciente) {
        this.idPaciente = paciente;
    }

    public byte[] getProfileImage() {
      return Arrays.copyOf(profileImage, profileImage.length);
    }

    public void setProfileImage(byte[] profileImage) {
      this.profileImage = Arrays.copyOf(profileImage, profileImage.length);
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", cadastroConfirmado=" + cadastroConfirmado +
                ", role=" + role +
                ", idPaciente=" + idPaciente +
                '}';
    }
}

