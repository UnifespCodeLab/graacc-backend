package org.codelab.graacc.Agendamentos.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;

@SpringBootTest
public class TokenServiceTest {
    @InjectMocks
    private TokenService tokenService;

    @BeforeEach
    public void setUpSecretVariables() throws Exception {
        Field secretVariable = TokenService.class.getDeclaredField("secret");
        secretVariable.setAccessible(true);
        secretVariable.set(tokenService, "minha-secret-teste");

        Field issuerVariable = TokenService.class.getDeclaredField("issuer");
        issuerVariable.setAccessible(true);
        issuerVariable.set(tokenService, "teste-emissor");
    }

//    @Test
//    @DisplayName("Validar token com sucesso")
//    public void testValidateTokenWithValidToken() {
//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ0ZXN0ZS1lbWlzc29yIiwic3ViIjoiZXhhbXBsZUBtYWlsLmNvbSIsImlkVXN1YXJpbyI6MSwiaWRQYWNpZW50ZSI6Miwicm9sZSI6IlJPTEVfVVNFUiIsImV4cCI6MTczOTQwMjcyOX0.UwCFcsZVGL7qv11-B6DqSq54w2eij1z-zMLKowT49w4";
//        UserLoggedInfo userInfo = tokenService.validateToken(token);
//
//        Assertions.assertNotNull(userInfo);
//        Assertions.assertEquals("example@mail.com", userInfo.getEmail());
//        Assertions.assertEquals(1L, userInfo.getIdUsuario());
//        Assertions.assertEquals(2L, userInfo.getIdPaciente());
//        Assertions.assertEquals("ROLE_USER", userInfo.getRole());
//    }

    @Test
    @DisplayName("Validar token nulo com erro")
    public void testValidateTokenWithNullToken() {
        UserLoggedInfo userInfo = tokenService.validateToken(null);
        Assertions.assertNull(userInfo);
    }

}
