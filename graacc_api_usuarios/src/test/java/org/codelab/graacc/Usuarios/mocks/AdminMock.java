package org.codelab.graacc.Usuarios.mocks;

import org.codelab.graacc.Usuarios.dto.request.AdminRegisterRequestDTO;
import org.codelab.graacc.Usuarios.dto.request.UserLoginRequestDTO;
import org.codelab.graacc.Usuarios.dto.request.UserRegisterRequestDTO;
import org.codelab.graacc.Usuarios.entity.Role;
import org.codelab.graacc.Usuarios.entity.UserEntity;

public class AdminMock {
    public static UserLoginRequestDTO adminLoginRequestMock() {
        UserLoginRequestDTO login = new UserLoginRequestDTO();
        login.setEmail("admin@example.com");
        login.setSenha("senha123");
        return login;
    }

    public static AdminRegisterRequestDTO adminRegisterRequestMock() {
        AdminRegisterRequestDTO user = new AdminRegisterRequestDTO();
        user.setNome("Joana Santos");
        user.setEmail("joana.santos@example.com");
        user.setSenha("12345678");
        return user;
    }

    public static UserEntity createAdminUserEntity() {
        UserEntity admin = new UserEntity();
        admin.setIdUsuario(1);
        admin.setNome("Admin Example");
        admin.setEmail("admin@example.com");
        admin.setSenha("$2a$10$dUb.8/5D42Eg11Vsxr7W0eTv5UIeZFGP1OpWpHf4n0u77EnjNjEBS");
        admin.setCadastroConfirmado(false);
        admin.setRole(Role.ADMIN.getDescricao());
        admin.setIdPaciente(null);
        return admin;
    }
}
