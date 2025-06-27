package org.codelab.graacc.Usuarios.mocks;

import org.codelab.graacc.Usuarios.dto.UserDTO;
import org.codelab.graacc.Usuarios.dto.UserLoggedInfo;
import org.codelab.graacc.Usuarios.dto.request.UserLoginRequestDTO;
import org.codelab.graacc.Usuarios.dto.request.UserRegisterRequestDTO;
import org.codelab.graacc.Usuarios.dto.request.UserUpdateRequestDTO;
import org.codelab.graacc.Usuarios.dto.response.UserLoginResponseDTO;
import org.codelab.graacc.Usuarios.entity.Role;
import org.codelab.graacc.Usuarios.entity.UserEntity;

public final class UserMock {

    public static UserEntity userEntityLogged() {
        UserEntity userLogged = new UserEntity();
        userLogged.setIdUsuario(1);
        userLogged.setNome("Ana Costa");
        userLogged.setEmail("ana.costa@example.com");
        userLogged.setSenha("$2a$10$dUb.8/5D42Eg11Vsxr7W0eTv5UIeZFGP1OpWpHf4n0u77EnjNjEBS");
        userLogged.setCadastroConfirmado(false);
        userLogged.setRole(Role.USER.getDescricao());
        userLogged.setIdPaciente(PatientMock.createPatientExisted().getIdPaciente());
        return userLogged;
    }

    public static UserLoggedInfo userLoggedInfo() {
        UserLoggedInfo userLogged = new UserLoggedInfo();
        userLogged.setIdUsuario(1L);
        userLogged.setEmail("ana.costa@example.com");
        userLogged.setRole(Role.USER.getDescricao());
        return userLogged;
    }

    public static UserLoginRequestDTO userLoginRequestSuccess() {
        UserLoginRequestDTO dto = new UserLoginRequestDTO();
        dto.setEmail("ana.costa@example.com");
        dto.setSenha("senha123");
        return dto;
    }

    public static UserLoginRequestDTO userLoginRequestWithWrongPassword() {
        UserLoginRequestDTO dto = new UserLoginRequestDTO();
        dto.setEmail("ana.costa@example.com");
        dto.setSenha("senhaErrada");
        return dto;
    }

    public static UserRegisterRequestDTO userRegisterRequest() {
        UserRegisterRequestDTO dto = new UserRegisterRequestDTO();
        dto.setNome("Joana Santos");
        dto.setEmail("joana.santos@example.com");
        dto.setSenha("12345678");
        dto.setNomeCompletoPaciente("Maria Oliveira Santos");
        return dto;
    }

    public static UserRegisterRequestDTO userRegisterRequestWithPatientInvalid() {
        UserRegisterRequestDTO dto = new UserRegisterRequestDTO();
        dto.setNome("Joana Santos");
        dto.setEmail("joana.santos@example.com");
        dto.setSenha("12345678");
        dto.setNomeCompletoPaciente("Kaique Santos");
        return dto;
    }

    public static UserLoginResponseDTO userLoginResponse() {
        return new UserLoginResponseDTO("Joana Santos", "tokenexemplo");
    }

    public static UserUpdateRequestDTO userUpdateRequest() {
        UserUpdateRequestDTO request = new UserUpdateRequestDTO();
        request.setNome("Ana Carolina Costa");
        return request;
    }

    public static UserDTO userFindedDTO() {
        return new UserDTO(1L, "Ana Costa", "ana.costa@example.com", false, Role.USER.getDescricao(), 1L);
    }
}
