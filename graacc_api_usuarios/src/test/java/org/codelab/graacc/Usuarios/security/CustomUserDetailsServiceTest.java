package org.codelab.graacc.Usuarios.security;

import org.codelab.graacc.Usuarios.mocks.UserMock;
import org.codelab.graacc.Usuarios.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootTest
@DisplayName("CustomUserDetailsServiceTest")
public class CustomUserDetailsServiceTest {
    @InjectMocks
    CustomUserDetailsService userDetailsService;
    @Mock
    UserRepository userRepositoryMock;

    @Test
    @DisplayName("Carrega usuario especifico a partir do email")
    void testLoadUserByUsernameSuccessfully() {
        Mockito
                .when(userRepositoryMock.findByEmail(Mockito.anyString()))
                .thenReturn(UserMock.userEntityLogged());
        var resposta = userDetailsService.loadUserByUsername("ana.costa@example.com");
        Assertions.assertEquals("ana.costa@example.com", resposta.getUsername());
        Assertions.assertTrue(resposta.getAuthorities().isEmpty());
    }

    @Test
    @DisplayName("Erro ao carregar usuario especifico a partir do email")
    void testLoadUserByUsernameThrowsException() {
        Mockito
                .when(userRepositoryMock.findByEmail(Mockito.anyString()))
                .thenReturn(null);

        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername("marcio.barbosa@example.com")
        );
    }
}
