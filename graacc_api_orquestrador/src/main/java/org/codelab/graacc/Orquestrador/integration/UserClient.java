package org.codelab.graacc.Orquestrador.integration;


import org.codelab.graacc.Orquestrador.dto.user.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "graacc-ms-usuarios",
        url = "${url.ms.usuarios}"
)
public interface UserClient {

    @PostMapping("/admin/registrar")
    ResponseEntity adicionarAdministrador(@RequestBody AdminRegisterRequest adminRegisterRequest);

    @PostMapping("/admin/login")
    UserLoginResponse loginAdministrador(@RequestBody UserLoginRequest userLoginRequest);

    @PostMapping("/usuarios/registrar")
    ResponseEntity adicionarUsuario(@RequestBody UserRegisterRequest userRegisterRequest);

    @PostMapping("/usuarios/login")
    UserLoginResponse loginUsuario(@RequestBody UserLoginRequest userLoginRequest);

    @PostMapping("/usuarios/confirmar")
    ResponseEntity confirmarCadastroUsuario(@RequestHeader("Authorization") String token);

    @GetMapping("/usuarios")
    UserDTO listarUsuario(@RequestHeader("Authorization") String token);

    @PutMapping("/usuarios")
    UserDTO atualizarUsuario(@RequestHeader("Authorization") String token,
                             @RequestBody UserUpdateRequest userUpdateRequest);

    @DeleteMapping("/usuarios")
    ResponseEntity deletarUsuario(@RequestHeader("Authorization") String token);


}
