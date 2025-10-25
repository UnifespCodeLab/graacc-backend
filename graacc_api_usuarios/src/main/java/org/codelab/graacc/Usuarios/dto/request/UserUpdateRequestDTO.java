package org.codelab.graacc.Usuarios.dto.request;
import org.springframework.web.multipart.MultipartFile;

public class UserUpdateRequestDTO {
    private String nome;
    private String email;
    private String nomeCompletoPaciente;
    private MultipartFile profileImage;

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

    public void setProfileImage(MultipartFile profileImage) {
      this.profileImage = profileImage;
    }

    public MultipartFile getProfileImage() {
      return profileImage;
    }
}
