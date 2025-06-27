package org.codelab.graacc.Usuarios.services;

import org.codelab.graacc.Usuarios.mocks.AdminMock;
import org.codelab.graacc.Usuarios.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class AdminServiceTest {
    @Mock
    UserRepository userRepositoryMock;
    @Mock
    PasswordEncoder passwordEncoderMock;

    @InjectMocks
    AdminService adminService;

    @Test
    @DisplayName("Adiciona Administrador com sucesso")
    void adicionaAdministradorComSucesso() {
        Mockito.when(passwordEncoderMock.encode(Mockito.anyString()))
                        .thenReturn("senhaencriptada");
        Mockito
                .when(userRepositoryMock.save(Mockito.any()))
                .thenReturn(AdminMock.createAdminUserEntity());
        var requisicao = AdminMock.adminRegisterRequestMock();
        adminService.addAdmin(requisicao);
        Mockito.verify(userRepositoryMock).save(Mockito.any());
    }
}
