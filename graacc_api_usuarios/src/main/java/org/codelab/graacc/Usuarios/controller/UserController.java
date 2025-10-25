package org.codelab.graacc.Usuarios.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.codelab.graacc.Usuarios.dto.UserDTO;
import org.codelab.graacc.Usuarios.dto.request.UserLoginRequestDTO;
import org.codelab.graacc.Usuarios.dto.request.UserRegisterRequestDTO;
import org.codelab.graacc.Usuarios.dto.request.UserUpdateRequestDTO;
import org.codelab.graacc.Usuarios.dto.response.UserLoginResponseDTO;
import org.codelab.graacc.Usuarios.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios")
public class UserController {
    @Autowired
    UserService userService;

    // sem token obrigatório
    @Operation(summary = "Registrar um novo Usuário.", description = "Não é obrigatório uso de token de autenticação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso.")
    })
    @PostMapping("/registrar")
    public ResponseEntity addUser(@Parameter(description = "Dados do novo Usuário",
                                            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserRegisterRequestDTO.class))})
                                 @RequestBody UserRegisterRequestDTO userRequest) {
        try {
            userService.addUser(userRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // sem token obrigatório
    @Operation(summary = "Login do Usuário.", description = "Não é obrigatório uso de token de autenticação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso.",
                         content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserLoginResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao logar usuário.")
    })
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@Parameter(description = "Dados de Login do Usuário",
                                                                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserLoginRequestDTO.class))})
                                                     @RequestBody UserLoginRequestDTO userLoginRequest) {
        UserLoginResponseDTO dto = userService.login(userLoginRequest);
        return (dto != null)
                ? ResponseEntity.ok(dto)
                : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Confirmar cadastro do Usuário.", description = "É obrigatório uso de token de autenticação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário confirmado com sucesso.")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/confirmar")
    public ResponseEntity confirmUser() {
        userService.confirmUser();
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Obter dados do Usuário.", description = "É obrigatório uso de token de autenticação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do Usuário obtidos com sucesso.",
                        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping()
    ResponseEntity<UserDTO> getUser() throws IOException {
        UserDTO user = userService.findUser();
        return (user != null)
                ? ResponseEntity.ok().body(user)
                : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Editar dados do Usuário.", description = "É obrigatório uso de token de autenticação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do Usuário editados com sucesso.")
    })
    @PutMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    ResponseEntity<String> updateUser(@Parameter(description = "Dados de Edição do Usuário",
                                                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserUpdateRequestDTO.class))})
                                     @RequestBody UserUpdateRequestDTO userRequest) throws IOException {
        try {
          userService.updateUser(userRequest);
          return ResponseEntity.ok().build();
        } catch (IOException e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image: " + e.getMessage());
        }
    }

    @Operation(summary = "Deletar dados do Usuário.", description = "É obrigatório uso de token de autenticação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do Usuário deletados com sucesso.")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping()
    ResponseEntity<String> deleteUser() {
        userService.deleteUser();
        return ResponseEntity.ok().build();
    }
}
