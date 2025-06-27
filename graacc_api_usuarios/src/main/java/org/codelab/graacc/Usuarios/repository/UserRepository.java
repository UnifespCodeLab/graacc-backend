package org.codelab.graacc.Usuarios.repository;

import org.codelab.graacc.Usuarios.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByIdUsuario(Long idUsuario);

    UserEntity findByEmail(String email);
}
