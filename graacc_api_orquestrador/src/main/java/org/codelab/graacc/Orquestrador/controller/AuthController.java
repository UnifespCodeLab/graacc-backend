package org.codelab.graacc.Orquestrador.controller;

import feign.FeignException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.codelab.graacc.Orquestrador.dto.user.*;
import org.codelab.graacc.Orquestrador.integration.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/graacc/api/auth")
public class AuthController {
    @Autowired
    UserClient userClient;

    @Operation(summary = "Registrar um novo Administrador.", description = "Não é obrigatório uso de token de autenticação.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Administrador registrado com sucesso.")})
    @PostMapping("/admin/registrar")
    public ResponseEntity adicionarAdministrador(@RequestBody AdminRegisterRequest request) {
        userClient.adicionarAdministrador(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Login do Administrador.", description = "Não é obrigatório uso de token de autenticação.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Administrador logado com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserLoginResponse.class))})})
    @PostMapping("/admin/login")
    public ResponseEntity<UserLoginResponse> loginAdministrador(@RequestBody UserLoginRequest request) {
        var resposta = userClient.loginAdministrador(request);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Registrar um novo Usuário.", description = "Não é obrigatório uso de token de autenticação.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso.")})
    @PostMapping("/usuario/registrar")
    public ResponseEntity adicionarUsuario(@RequestBody UserRegisterRequest request) {
        userClient.adicionarUsuario(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Login do Usuário.", description = "Não é obrigatório uso de token de autenticação.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuário logado com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserLoginResponse.class))})})
    @PostMapping("/usuario/login")
    public ResponseEntity<UserLoginResponse> loginUsuario(@RequestBody UserLoginRequest request) {
        var resposta = userClient.loginUsuario(request);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Confirmar cadastro do Usuário.", description = "É obrigatório uso de token de autenticação.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Cadastro do Usuário confirmado com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))})})
    @PostMapping("/usuario/confirmar-cadastro")
    public ResponseEntity<UserDTO> confirmarCadastroUsuario(@RequestHeader("Authorization") String token) {
        userClient.confirmarCadastroUsuario(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Obter dados do Usuário.", description = "É obrigatório uso de token de autenticação.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuário listado com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))})})
    @GetMapping("/usuario/listar")
    public ResponseEntity<UserDTO> listarUsuario(@RequestHeader("Authorization") String token) {
        var resposta = userClient.listarUsuario(token);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Editar dados do Usuário.", description = "É obrigatório uso de token de autenticação.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso.", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))})})
    @PutMapping("/usuario/editar")
    public ResponseEntity<UserDTO> atualizarUsuario(@RequestHeader("Authorization") String token, @RequestBody UserUpdateRequest userUpdateRequest) {
        var resposta = userClient.atualizarUsuario(token, userUpdateRequest);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @Operation(summary = "Deletar dados do Usuário.", description = "É obrigatório uso de token de autenticação.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso.")})
    @DeleteMapping("/usuario/deletar")
    public ResponseEntity<UserDTO> deletarUsuario(@RequestHeader("Authorization") String token) {
        userClient.deletarUsuario(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignErrors(FeignException feignException) {
        String mensagem;
        try {
            mensagem = feignException.contentUTF8();
            if (mensagem == null || mensagem.isBlank()) {
                mensagem = "Erro ao comunicar com serviço externo.";
            }
            return ResponseEntity.status(feignException.status()).body(mensagem);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro no processamento da solicitação.");
        }
    }
}
