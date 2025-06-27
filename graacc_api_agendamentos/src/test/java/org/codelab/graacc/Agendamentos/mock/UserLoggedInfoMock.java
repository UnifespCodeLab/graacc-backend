package org.codelab.graacc.Agendamentos.mock;

import org.codelab.graacc.Agendamentos.security.UserLoggedInfo;

public class UserLoggedInfoMock {

    public static UserLoggedInfo userLoggedInfoMock() {
        UserLoggedInfo userLoggedInfo = new UserLoggedInfo();
        userLoggedInfo.setIdUsuario(1L);
        userLoggedInfo.setEmail("ana.costa@example.com");
        userLoggedInfo.setRole("ROLE_USER");
        userLoggedInfo.setIdPaciente(1L);
        return userLoggedInfo;
    }
}
