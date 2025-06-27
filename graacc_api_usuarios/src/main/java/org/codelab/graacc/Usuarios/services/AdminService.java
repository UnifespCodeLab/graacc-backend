package org.codelab.graacc.Usuarios.services;

import jakarta.transaction.Transactional;
import org.codelab.graacc.Usuarios.dto.request.AdminRegisterRequestDTO;
import org.codelab.graacc.Usuarios.entity.Role;
import org.codelab.graacc.Usuarios.entity.UserEntity;
import org.codelab.graacc.Usuarios.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    public void addAdmin(AdminRegisterRequestDTO adminRequest) {
        UserEntity userEntity = new UserEntity(
                adminRequest.getNome(),
                adminRequest.getEmail(),
                passwordEncoder.encode(adminRequest.getSenha()),
                false,
                Role.ADMIN.getDescricao(),
                null
        );

        try {
            userRepository.save(userEntity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir usuario ADMIN.");
        }
    }
}
