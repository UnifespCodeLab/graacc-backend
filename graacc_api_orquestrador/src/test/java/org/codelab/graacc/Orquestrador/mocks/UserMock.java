package org.codelab.graacc.Orquestrador.mocks;

import org.codelab.graacc.Orquestrador.dto.user.*;

public class UserMock {

    public static UserDTO userDTOMock() {
        UserDTO dto = new UserDTO();
        dto.setNome("Ana Costa");
        dto.setIdUsuario(1L);
        dto.setIdPaciente(1L);
        dto.setRole("ROLE_USER");
        dto.setEmail("ana.costa@example.com");
        dto.setCadastroConfirmado(true);
        return dto;
    }

    public static UserLoginRequest userLoginRequestMock() {
        UserLoginRequest login = new UserLoginRequest();
        login.setEmail("ana.costa@example.com");
        login.setSenha("senha123");
        return login;
    }

    public static UserLoginRequest adminLoginRequestMock() {
        UserLoginRequest login = new UserLoginRequest();
        login.setEmail("admin@example.com");
        login.setSenha("senha123");
        return login;
    }

    public static UserRegisterRequest userRegisterRequestMock() {
        UserRegisterRequest user = new UserRegisterRequest();
        user.setNome("Joana Santos");
        user.setEmail("joana.santos@example.com");
        user.setSenha("12345678");
        user.setNomeCompletoPaciente("Maria Oliveira Santos");
        return user;
    }

    public static AdminRegisterRequest adminRegisterRequestMock() {
        AdminRegisterRequest admin = new AdminRegisterRequest();
        admin.setNome("Lorem Ipsum");
        admin.setEmail("admin@example.com");
        admin.setSenha("12345678");
        return admin;
    }

    public static UserLoginResponse userLoginResponseMock() {
        UserLoginResponse response = new UserLoginResponse();
        response.setNome("Joana Santos");
        response.setToken("tokenexemplo");
        return response;
    }

    public static UserLoginResponse adminLoginResponseMock() {
        UserLoginResponse response = new UserLoginResponse();
        response.setNome("Lorem Ipsum");
        response.setToken("tokenexemplo");
        return response;
    }

    public static UserUpdateRequest userUpdateRequestMock() {
        UserUpdateRequest request = new UserUpdateRequest();
        request.setNome("Joana Santos Barbosa");
        request.setEmail("joana.santos@example.com");
        request.setNomeCompletoPaciente("Maria Oliveira Santos");
        return request;
    }
}
