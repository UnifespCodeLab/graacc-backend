package org.codelab.graacc.Orquestrador.controller;


import feign.FeignException;
import org.codelab.graacc.Orquestrador.integration.UserClient;
import org.codelab.graacc.Orquestrador.mocks.UserMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class AuthControllerTest {
    @Mock
    UserClient userClientMock;

    @InjectMocks
    AuthController authController;

    private String token = "token-exemplo";

    @Test
    @DisplayName("Adicionar Administrador com sucesso")
    void adicionarAdministradorComSucesso() {
        Mockito.when(userClientMock.adicionarAdministrador(Mockito.any()))
                .thenReturn(ResponseEntity.ok().build());
        var resposta = authController.adicionarAdministrador(UserMock.adminRegisterRequestMock());
        Assertions.assertNotNull(resposta);
        Assertions.assertNotNull(UserMock.adminRegisterRequestMock().getEmail());
        Assertions.assertNotNull(UserMock.adminRegisterRequestMock().getNome());
        Assertions.assertNotNull(UserMock.adminRegisterRequestMock().getSenha());

    }

    @Test
    @DisplayName("Login Administrador com sucesso")
    void loginAdministradorComSucesso() {
        Mockito.when(userClientMock.loginAdministrador(Mockito.any()))
                .thenReturn(UserMock.adminLoginResponseMock());
        var resposta = authController.loginAdministrador(UserMock.adminLoginRequestMock());
        Assertions.assertNotNull(resposta);
        Assertions.assertEquals(UserMock.adminLoginResponseMock().getNome(), resposta.getBody().getNome());
        Assertions.assertEquals(UserMock.adminLoginResponseMock().getToken(), resposta.getBody().getToken());
        Assertions.assertNotNull(UserMock.adminLoginRequestMock().getEmail());
        Assertions.assertNotNull(UserMock.adminLoginRequestMock().getSenha());
    }

    @Test
    @DisplayName("Adicionar Usuario com sucesso")
    void adicionarUsuarioComSucesso() {
        Mockito.when(userClientMock.adicionarUsuario(Mockito.any()))
                .thenReturn(ResponseEntity.ok().build());
        var resposta = authController.adicionarUsuario(UserMock.userRegisterRequestMock());
        Assertions.assertNotNull(resposta);
        Assertions.assertNotNull(UserMock.userRegisterRequestMock().getNome());
        Assertions.assertNotNull(UserMock.userRegisterRequestMock().getEmail());
        Assertions.assertNotNull(UserMock.userRegisterRequestMock().getSenha());
        Assertions.assertNotNull(UserMock.userRegisterRequestMock().getNomeCompletoPaciente());
    }

    @Test
    @DisplayName("Login Usuario com sucesso")
    void loginUsuarioComSucesso() {
        Mockito.when(userClientMock.loginUsuario(Mockito.any()))
                .thenReturn(UserMock.userLoginResponseMock());
        var resposta = authController.loginUsuario(UserMock.userLoginRequestMock());
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Confirmar cadastro do Usuario com sucesso")
    void confirmarCadastroUsuarioComSucesso() {
        Mockito.when(userClientMock.confirmarCadastroUsuario(Mockito.anyString()))
                .thenReturn(ResponseEntity.ok().build());
        var resposta = authController.confirmarCadastroUsuario(token);
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Listar Usuario com sucesso")
    void listarUsuarioComSucesso() {
        Mockito.when(userClientMock.listarUsuario(Mockito.anyString()))
                .thenReturn(UserMock.userDTOMock());
        var resposta = authController.listarUsuario(token);
        Assertions.assertNotNull(resposta);
        Assertions.assertEquals(UserMock.userDTOMock().getNome(), resposta.getBody().getNome());
        Assertions.assertEquals(UserMock.userDTOMock().getIdPaciente(), resposta.getBody().getIdPaciente());
        Assertions.assertEquals(UserMock.userDTOMock().getIdUsuario(), resposta.getBody().getIdUsuario());
        Assertions.assertEquals(UserMock.userDTOMock().getEmail(), resposta.getBody().getEmail());
        Assertions.assertEquals(UserMock.userDTOMock().getRole(), resposta.getBody().getRole());
        Assertions.assertTrue(resposta.getBody().isCadastroConfirmado());
    }

    @Test
    @DisplayName("Atualizar Usuario com sucesso")
    void atualizarUsuarioComSucesso() {
        Mockito.when(userClientMock.atualizarUsuario(Mockito.anyString(), Mockito.any()))
                .thenReturn(UserMock.userDTOMock());
        var resposta = authController.atualizarUsuario(token, UserMock.userUpdateRequestMock());
        Assertions.assertNotNull(resposta);
        Assertions.assertNotNull(UserMock.userUpdateRequestMock().getNome());
        Assertions.assertNotNull(UserMock.userUpdateRequestMock().getEmail());
        Assertions.assertNotNull(UserMock.userUpdateRequestMock().getNomeCompletoPaciente());
    }

    @Test
    @DisplayName("Deletar Usuario com sucesso")
    void deletarUsuarioComSucesso() {
        Mockito.when(userClientMock.deletarUsuario(Mockito.anyString()))
                .thenReturn(ResponseEntity.ok().build());
        var resposta = authController.deletarUsuario(token);
        Assertions.assertNotNull(resposta);
    }

    @Test
    @DisplayName("Testar lidar com excecoes FeignClient com sucesso")
    void testHandleFeignErrorsWithContentSucessoStatus400() {
        FeignException feignException = Mockito.mock(FeignException.class);
        Mockito.when(feignException.contentUTF8()).thenReturn("Erro específico do serviço externo");
        Mockito.when(feignException.status()).thenReturn(400);

        ResponseEntity<String> response = authController.handleFeignErrors(feignException);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("Erro específico do serviço externo", response.getBody());
    }

    @Test
    @DisplayName("Testar lidar com excecoes FeignClient com sucesso")
    void testHandleFeignErrorsWithContentSucessoStatus500() {
        FeignException feignException = Mockito.mock(FeignException.class);
        Mockito.when(feignException.contentUTF8()).thenReturn("");
        Mockito.when(feignException.status()).thenReturn(500);

        ResponseEntity<String> response = authController.handleFeignErrors(feignException);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("Erro ao comunicar com serviço externo.", response.getBody());
    }

    @Test
    @DisplayName("Testar lidar com excecoes FeignClient com sucesso - IllegalException")
    void testHandleFeignErrorsWithContentSucessoIllegalArgumentException() {
        FeignException feignException = Mockito.mock(FeignException.class);
        Mockito.when(feignException.contentUTF8()).thenThrow(IllegalArgumentException.class);

        ResponseEntity<String> response = authController.handleFeignErrors(feignException);

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assertions.assertEquals("Erro no processamento da solicitação.", response.getBody());
    }

}
