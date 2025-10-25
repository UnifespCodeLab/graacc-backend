package org.codelab.graacc.Usuarios.services;

import org.codelab.graacc.Usuarios.dto.UserLoggedInfo;
import org.codelab.graacc.Usuarios.dto.request.UserLoginRequestDTO;
import org.codelab.graacc.Usuarios.dto.request.UserUpdateRequestDTO;
import org.codelab.graacc.Usuarios.entity.UserEntity;
import org.codelab.graacc.Usuarios.mocks.PatientMock;
import org.codelab.graacc.Usuarios.mocks.UserMock;
import org.codelab.graacc.Usuarios.repository.UserRepository;
import org.codelab.graacc.Usuarios.security.token.TokenService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.io.IOException;

@SpringBootTest
@DisplayName("UserServiceTest")
public class UserServiceTest {
    @InjectMocks
    UserService service;

    @Mock PatientService patientServiceMock;
    @Mock UserRepository userRepositoryMock;
    @Mock PasswordEncoder passwordEncoderMock;
    @Mock TokenService tokenServiceMock;

    private void securityMockSetup() {
        UserLoggedInfo userLogged = UserMock.userLoggedInfo();
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(authentication.getPrincipal()).thenReturn(userLogged);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    @DisplayName("Adiciona usuario com sucesso")
    void testAddUserSuccessfully() {
        Mockito
                .when(patientServiceMock.findPatientByName(Mockito.anyString()))
                .thenReturn(PatientMock.createPatientExisted());
        var requisicao = UserMock.userRegisterRequest();
        service.addUser(requisicao);
        Mockito.verify(userRepositoryMock).save(Mockito.any());
    }

    @Test
    @DisplayName("Adiciona usuario com erro, pois paciente associado não existe.")
    void testAddUserReturnsException() {
        Mockito
                .when(patientServiceMock.findPatientByName(Mockito.anyString()))
                .thenReturn(null);
        var requisicao = UserMock.userRegisterRequestWithPatientInvalid();
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> service.addUser(requisicao)
        );
    }

    @Test
    @DisplayName("Login do usuario com sucesso")
    void testLoginSuccessfully() {
        UserLoginRequestDTO requisicaoLogin = UserMock.userLoginRequestSuccess();
        Mockito
                .when(userRepositoryMock.findByEmail("ana.costa@example.com"))
                .thenReturn(UserMock.userEntityLogged());
        Mockito
                .when(passwordEncoderMock.matches(
                        requisicaoLogin.getSenha(),
                        UserMock.userEntityLogged().getSenha()
                )).thenReturn(true);
        Mockito
                .when(tokenServiceMock.generateToken(Mockito.any(UserEntity.class)))
                .thenReturn("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJtcy11c3VhcmlvcyIsInN1YiI6ImpvYW96aW5ob0BnbWFpbC5jb20iLCJleHAiOjE3MzM3MDQ1NTl9.NDgGEML65PAF18Db8G5mtuDTy118Ifc4TpFTwsfQ_Rs");

         var resposta = service.login(requisicaoLogin);

        Assertions.assertEquals("Ana Costa", resposta.getNome());
        Assertions.assertEquals(169, resposta.getToken().length());
    }

    @Test
    @DisplayName("Login do usuario com senha errada retorna nulo")
    void testLoginWithError() {
        UserLoginRequestDTO requisicaoLogin = UserMock.userLoginRequestWithWrongPassword();
        Mockito
                .when(userRepositoryMock.findByEmail("ana.costa@example.com"))
                .thenReturn(UserMock.userEntityLogged());
        Mockito
                .when(passwordEncoderMock.matches(
                        requisicaoLogin.getSenha(),
                        UserMock.userEntityLogged().getSenha()
                )).thenReturn(false);
        var resposta = service.login(requisicaoLogin);
        Assertions.assertNull(resposta);
    }

    @Test
    @DisplayName("Confirma cadastro do usuario com sucesso")
    void testConfirmUserSuccessfully() {
        securityMockSetup();
        Mockito
                .when(userRepositoryMock.findByIdUsuario(1L))
                .thenReturn(UserMock.userEntityLogged());

        service.confirmUser();
        Mockito.verify(userRepositoryMock).save(Mockito.any());
    }

    @Test
    @DisplayName("Lista usuario com sucesso")
    void testFindUserSuccessfully() {
        securityMockSetup();
        Mockito
                .when(userRepositoryMock.findByIdUsuario(1L))
                .thenReturn(UserMock.userEntityLogged());

        var userDTO = service.findUser();
        Assertions.assertNotNull(userDTO);
        Assertions.assertEquals("Ana Costa", userDTO.getNome());
    }

    @Test
    @DisplayName("Atualiza usuario com sucesso")
    void testUpdateUserSuccessfully() throws IOException {
        securityMockSetup();
        UserUpdateRequestDTO requisicao = new UserUpdateRequestDTO();
        requisicao.setEmail("ana.costa@gmail.com");

        Mockito
                .when(userRepositoryMock.findByIdUsuario(1L))
                .thenReturn(UserMock.userEntityLogged());

        service.updateUser(requisicao);
        Mockito.verify(userRepositoryMock).save(Mockito.any());
    }

    @Test
    @DisplayName("Deleta usuario com sucesso")
    void testDeleteUserSuccessfully() {
        securityMockSetup();
        Mockito.doNothing()
                .when(userRepositoryMock).deleteById(Mockito.anyLong());
        Assertions.assertDoesNotThrow(
                () -> service.deleteUser()
        );
    }


}
