package org.codelab.graacc.Usuarios.services;

import jakarta.transaction.Transactional;
import org.codelab.graacc.Usuarios.dto.UserDTO;
import org.codelab.graacc.Usuarios.dto.UserLoggedInfo;
import org.codelab.graacc.Usuarios.dto.request.UserLoginRequestDTO;
import org.codelab.graacc.Usuarios.dto.request.UserRegisterRequestDTO;
import org.codelab.graacc.Usuarios.dto.request.UserUpdateRequestDTO;
import org.codelab.graacc.Usuarios.dto.response.UserLoginResponseDTO;
import org.codelab.graacc.Usuarios.entity.Role;
import org.codelab.graacc.Usuarios.entity.UserEntity;
import org.codelab.graacc.Usuarios.repository.UserRepository;
import org.codelab.graacc.Usuarios.security.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TokenService tokenService;
    @Autowired
    PatientService patientService;

    @Transactional
    public void addUser(UserRegisterRequestDTO userRequest) {
        var pacienteAssociado = patientService.findPatientByName(userRequest.getNomeCompletoPaciente());

        if (pacienteAssociado == null) {
            throw new IllegalArgumentException("Não existe nenhum paciente com esse nome");
        }

        UserEntity userEntity = new UserEntity(
                userRequest.getNome(),
                userRequest.getEmail(),
                passwordEncoder.encode(userRequest.getSenha()),
                false,
                Role.USER.getDescricao(),
                pacienteAssociado.getIdPaciente()
        );

        try {
            userRepository.save(userEntity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inserir usuario.");
        }
    }

    @Transactional
    public UserLoginResponseDTO login(UserLoginRequestDTO userRequest) {
        UserEntity entity = userRepository.findByEmail(userRequest.getEmail());

        if (entity == null) throw new RuntimeException("Usuario nao encontrado");

        if(passwordEncoder.matches(userRequest.getSenha(), entity.getSenha())) {
            String token = tokenService.generateToken(entity);
            return new UserLoginResponseDTO(entity.getNome(), token);
        }

        return null;
    }

    public void confirmUser() {
        UserLoggedInfo userLogged = getUserFromToken();
        UserEntity userEntity = userRepository.findByIdUsuario(userLogged.getIdUsuario());
        if (userEntity != null) {
            userEntity.setCadastroConfirmado(true);
            userRepository.save(userEntity);
        } else {
            throw new IllegalArgumentException("Não foi possivel atualizar pois o Usuario nao existe");
        }
    }

    public UserDTO findUser() {
        UserLoggedInfo userLogged = getUserFromToken();
        UserEntity user = userRepository.findByIdUsuario(userLogged.getIdUsuario());
        if (user != null) {
            UserDTO dto = new UserDTO();
            dto.setIdUsuario(user.getIdUsuario());
            dto.setNome(user.getNome());
            dto.setEmail(user.getEmail());
            dto.setCadastroConfirmado(user.getCadastroConfirmado());
            dto.setRole(user.getRole());
            dto.setIdPaciente(user.getIdPaciente());
            return dto;
        }
        return null;
    }

    public void updateUser(UserUpdateRequestDTO userRequest) throws IOException {
        UserLoggedInfo userLogged = getUserFromToken();
        UserEntity userEntity = userRepository.findByIdUsuario(userLogged.getIdUsuario());
        if (userEntity != null) {
            if (userRequest.getNome() != null) userEntity.setNome(userRequest.getNome());
            if (userRequest.getEmail() != null) userEntity.setEmail(userRequest.getEmail());
            if (userRequest.getProfileImage() != null) userEntity.setProfileImage(userRequest.getProfileImage().getBytes());
            if (userRequest.getNomeCompletoPaciente() != null) {
                var pacienteAssociado = patientService.findPatientByName(userRequest.getNomeCompletoPaciente());
                userEntity.setIdPaciente(pacienteAssociado.getIdPaciente());
            }
            userRepository.save(userEntity);
        } else {
            throw new IllegalArgumentException("Não foi possível atualizar o Usuário.");
        }
    }

    public void deleteUser() {
        UserLoggedInfo userLogged = getUserFromToken();
       try {
           userRepository.deleteById(userLogged.getIdUsuario());
       } catch (Exception e) {
            throw new IllegalArgumentException("Não existe nenhum usuario com esse id");
       }
    }

    private UserLoggedInfo getUserFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserLoggedInfo user = (UserLoggedInfo) authentication.getPrincipal();
        if (user == null) throw new RuntimeException("Token inválido.");
        return user;
    }

}
