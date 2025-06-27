package org.codelab.graacc.Usuarios.security.token;

import org.codelab.graacc.Usuarios.dto.UserLoggedInfo;
import org.codelab.graacc.Usuarios.mocks.UserMock;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;

@SpringBootTest
@DisplayName("TokenServiceTest")
public class TokenServiceTest {
    @InjectMocks
    TokenService tokenService;

    @BeforeEach
    void setUpSecretVariables() throws Exception {
        Field secretVariable = TokenService.class.getDeclaredField("secret");
        secretVariable.setAccessible(true);
        secretVariable.set(tokenService, "minha-secret-teste");

        Field issuerVariable = TokenService.class.getDeclaredField("issuer");
        issuerVariable.setAccessible(true);
        issuerVariable.set(tokenService, "teste-emissor");
    }

    @Test
    @DisplayName("Gera token com sucesso")
    void testGenerateTokenSuccessfully() throws Exception {
        String token = tokenService.generateToken(UserMock.userEntityLogged());
        Assertions.assertFalse(token.isBlank());
    }

    /*@Test
    @DisplayName("Gera token com erro devido a chave invalida, lanca excecao.")
    void testGenerateTokenThrowsException() throws Exception {
        RuntimeException exception = Assertions.assertThrows(
                RuntimeException.class,
                () -> tokenService.generateToken(new UserEntity())
        );
        Assertions.assertEquals("Erro ao autenticar", exception.getMessage());
    }*/

    @Test
    @DisplayName("Valida token com sucesso")
    void testValidateTokenSuccessfully() throws Exception {
        String generatedToken = tokenService.generateToken(UserMock.userEntityLogged());
        UserLoggedInfo userValidated = tokenService.validateToken(generatedToken);
        Assertions.assertEquals("ana.costa@example.com", userValidated.getEmail());
        Assertions.assertEquals("ROLE_USER", userValidated.getRole());
    }

    @Test
    @DisplayName("Valida token com falha")
    void testValidateTokenReturnsNull() throws Exception {
        UserLoggedInfo validated = tokenService.validateToken("tokeninvalido123");
        Assertions.assertNull(validated);
    }

}
