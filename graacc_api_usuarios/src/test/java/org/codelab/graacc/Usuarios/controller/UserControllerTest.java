package org.codelab.graacc.Usuarios.controller;

import org.codelab.graacc.Usuarios.dto.request.UserUpdateRequestDTO;
import org.codelab.graacc.Usuarios.mocks.UserMock;
import org.codelab.graacc.Usuarios.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
@DisplayName("UserControllerTest")
public class UserControllerTest {
    @InjectMocks
    UserController controller;
    @Mock
    UserService userServiceMock;

    @Test
    @DisplayName("Adiciona usuario com sucesso")
    void testEndpointAddUserSuccessfully() {
        var requisicao = UserMock.userRegisterRequest();
        Mockito.doNothing()
                .when(userServiceMock).addUser(requisicao);

        var resposta = controller.addUser(requisicao);
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    @DisplayName("Login do usuario com sucesso")
    void testEndpointLoginSuccessfully() {
        var requisicao = UserMock.userLoginRequestSuccess();
        Mockito
                .when(userServiceMock.login(requisicao))
                .thenReturn(UserMock.userLoginResponse());

        var resposta = controller.login(requisicao);
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals("Joana Santos", resposta.getBody().getNome());
    }

    @Test
    @DisplayName("Confirma cadastro do usuario com sucesso")
    void testEndpointConfirmUserSuccessfully() {
        Mockito.doNothing()
                .when(userServiceMock).confirmUser();
        var resposta = controller.confirmUser();
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    @DisplayName("Pesquisa usuario com sucesso")
    void testEndpointFindUserSuccessfully() {
        Mockito
                .when(userServiceMock.findUser())
                .thenReturn(UserMock.userFindedDTO());
        var resposta = controller.getUser();
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals("Ana Costa", resposta.getBody().getNome());
        Assertions.assertNotNull(resposta.getBody().getEmail());
        Assertions.assertNotNull(resposta.getBody().getRole());
        Assertions.assertNotNull(resposta.getBody().getIdUsuario());
        Assertions.assertNotNull(resposta.getBody().getIdPaciente());
    }

    @Test
    @DisplayName("Pesquisa usuario, nao encontra e retorna vazio.")
    void testEndpointFindUserNotFound() {
        Mockito
                .when(userServiceMock.findUser())
                .thenReturn(null);
        var resposta = controller.getUser();
        Assertions.assertEquals(HttpStatus.NOT_FOUND, resposta.getStatusCode());
    }

    @Test
    @DisplayName("Atualiza usuario com sucesso")
    void testEndpointUpdateUserSuccessfully() {
        var requisicao = UserMock.userUpdateRequest();
        Mockito.doNothing()
                .when(userServiceMock).updateUser(Mockito.any(UserUpdateRequestDTO.class));
        var resposta = controller.updateUser(requisicao);
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    @DisplayName("Deleta usuario com sucesso")
    void testEndpointDeleteUserSuccessfully() {
        Mockito.doNothing()
                .when(userServiceMock).deleteUser();
        var resposta = controller.deleteUser();
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }
}
