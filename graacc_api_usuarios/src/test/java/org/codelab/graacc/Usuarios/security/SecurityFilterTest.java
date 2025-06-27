package org.codelab.graacc.Usuarios.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.codelab.graacc.Usuarios.dto.UserLoggedInfo;
import org.codelab.graacc.Usuarios.mocks.UserMock;
import org.codelab.graacc.Usuarios.security.token.TokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;


@SpringBootTest
@DisplayName("SecurityFilterTest")
public class SecurityFilterTest {
    @InjectMocks
    SecurityFilter securityFilter;

    @Mock
    TokenService tokenServiceMock;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Test
    @DisplayName("Aplicar filtro com sucesso, para usuario autenticado")
    void testDoFilterInternalSuccessfullyUserAuthenticated() throws Exception {
        String token = "meutokenexemplo";
        String email = "ana.costa@example.com";
        UserLoggedInfo user = new UserLoggedInfo(1L, 2L, email, "ROLE_USER");

        Mockito
                .when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        Mockito
                .when(tokenServiceMock.validateToken(token))
                .thenReturn(user);

        securityFilter.doFilterInternal(request, response, filterChain);

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Assertions.assertNotNull(authentication);
        Assertions.assertEquals(email, ((UserLoggedInfo) authentication.getPrincipal()).getEmail());
        Assertions.assertTrue(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
        Mockito.verify(filterChain).doFilter(request, response);
    }

}
