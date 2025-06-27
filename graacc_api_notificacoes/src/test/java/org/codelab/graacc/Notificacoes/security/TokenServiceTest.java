package org.codelab.graacc.Notificacoes.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.util.Date;

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

    @Test
    @DisplayName("Validar token com sucesso")
    public void testValidateTokenWithValidToken() {
        Algorithm algorithm = Algorithm.HMAC256("minha-secret-teste");
        String token = JWT.create()
                .withIssuer("teste-emissor")
                .withSubject("example@mail.com")
                .withClaim("idUsuario", 1L)
                .withClaim("idPaciente", 2L)
                .withClaim("role", "ROLE_USER")
                .withIssuedAt(new Date())
                .sign(algorithm);

        UserLoggedInfo userInfo = tokenService.validateToken(token);

        Assertions.assertNotNull(userInfo);
        Assertions.assertEquals("example@mail.com", userInfo.getEmail());
        Assertions.assertEquals(1L, userInfo.getIdUsuario());
        Assertions.assertEquals(2L, userInfo.getIdPaciente());
        Assertions.assertEquals("ROLE_USER", userInfo.getRole());
    }

    @Test
    @DisplayName("Validar token nulo com erro")
    public void testValidateTokenWithNullToken() {
        UserLoggedInfo userInfo = tokenService.validateToken(null);
        Assertions.assertNull(userInfo);
        userInfo = new UserLoggedInfo();
        userInfo.setEmail(null);
        userInfo.setIdPaciente(null);
        userInfo.setIdPaciente(null);
        userInfo.setRole(null);
    }

}
