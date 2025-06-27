package org.codelab.graacc.Usuarios.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.codelab.graacc.Usuarios.dto.UserLoggedInfo;
import org.codelab.graacc.Usuarios.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.seguranca.token.secret}")
    private String secret;
    @Value("${api.seguranca.token.emissor}")
    private String issuer;

    public String generateToken(UserEntity user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.getEmail())
                    .withClaim("idUsuario", user.getIdUsuario())
                    .withClaim("idPaciente", (user.getIdPaciente() != null) ? user.getIdPaciente() : null)
                    .withClaim("role", user.getRole())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao autenticar");
        }
    }

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

    private Instant generateExpirationDate() {
        return LocalDateTime.now()
                .plusHours(24)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
