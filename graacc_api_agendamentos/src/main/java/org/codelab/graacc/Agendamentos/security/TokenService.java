package org.codelab.graacc.Agendamentos.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Value("${api.seguranca.token.secret}")
    private String secret;
    @Value("${api.seguranca.token.emissor}")
    private String issuer;

    public UserLoggedInfo validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            var jwt = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token);
            String username = jwt.getSubject();
            Long idUsuario = jwt.getClaim("idUsuario").asLong();
            Long idPaciente = jwt.getClaim("idPaciente").asLong();
            String role = jwt.getClaim("role").asString();
            return new UserLoggedInfo(idUsuario, idPaciente, username, role);
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}
