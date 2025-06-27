package org.codelab.graacc.Usuarios.controller;

import org.codelab.graacc.Usuarios.mocks.AdminMock;
import org.codelab.graacc.Usuarios.mocks.UserMock;
import org.codelab.graacc.Usuarios.services.AdminService;
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
@DisplayName("AdminControllerTest")
public class AdminControllerTest {
    @InjectMocks
    AdminController controller;
    @Mock
    AdminService adminServiceMock;
    @Mock
    UserService userServiceMock;

    @Test
    @DisplayName("Adiciona usuario com sucesso")
    void testEndpointAddUserSuccessfully() {
        var requisicao = AdminMock.adminRegisterRequestMock();
        Mockito.doNothing()
                .when(adminServiceMock).addAdmin(requisicao);

        var resposta = controller.addUser(requisicao);
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

    @Test
    @DisplayName("Login do usuario com sucesso")
    void testEndpointLoginSuccessfully() {
        var requisicao = AdminMock.adminLoginRequestMock();
        Mockito
                .when(userServiceMock.login(requisicao))
                .thenReturn(UserMock.userLoginResponse());

        var resposta = controller.login(requisicao);
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals("Joana Santos", resposta.getBody().getNome());
    }
}
