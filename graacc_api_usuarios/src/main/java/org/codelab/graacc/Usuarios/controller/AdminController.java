package org.codelab.graacc.Usuarios.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.codelab.graacc.Usuarios.dto.request.AdminRegisterRequestDTO;
import org.codelab.graacc.Usuarios.dto.request.UserLoginRequestDTO;
import org.codelab.graacc.Usuarios.dto.response.UserLoginResponseDTO;
import org.codelab.graacc.Usuarios.services.AdminService;
import org.codelab.graacc.Usuarios.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Tag(name = "Administrador")
public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;

    @PostMapping("/registrar")
    public ResponseEntity addUser(@Parameter(description = "Dados do novo Administrador",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = AdminRegisterRequestDTO.class))})
                                  @RequestBody AdminRegisterRequestDTO adminRequest) {
        try {
            adminService.addAdmin(adminRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @Operation(summary = "Login do Administrador.", description = "Não é obrigatório uso de token de autenticação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Administrador logado com sucesso.",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserLoginResponseDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Erro ao logar Administrador.")
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

}
