package org.codelab.graacc.Notificacoes.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;

@SpringBootTest
public class SecurityFilterTest {
    @Mock
    private TokenService tokenService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private SecurityFilter securityFilter;

    @Test
    public void testDoFilterInternalWithValidToken() throws Exception {
        String token = "valid-token";
        UserLoggedInfo userInfo = new UserLoggedInfo(1L, 1L, "example@mail.com", "ROLE_USER");
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        Mockito.when(tokenService.validateToken(token)).thenReturn(userInfo);

        securityFilter.doFilterInternal(request, response, filterChain);

        Mockito.verify(tokenService).validateToken(token);
        Mockito.verify(filterChain).doFilter(request, response);

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Assertions.assertNotNull(authentication);
        Assertions.assertEquals(userInfo, authentication.getPrincipal());
        Assertions.assertEquals(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")), authentication.getAuthorities());
    }

    @Test
    public void testDoFilterInternalWithInvalidToken() throws Exception {
        String token = "invalid-token";
        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        Mockito.when(tokenService.validateToken(token)).thenReturn(null);

        securityFilter.doFilterInternal(request, response, filterChain);

        Mockito.verify(tokenService).validateToken(token);
        Mockito.verify(filterChain).doFilter(request, response);

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Assertions.assertNull(authentication);
    }

    @Test
    public void testDoFilterInternalWithNoToken() throws Exception {
        Mockito.when(request.getHeader("Authorization")).thenReturn(null);

        securityFilter.doFilterInternal(request, response, filterChain);

        Mockito.verify(tokenService, Mockito.never()).validateToken(Mockito.anyString());
        Mockito.verify(filterChain).doFilter(request, response);

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        Assertions.assertNull(authentication);
    }
}
