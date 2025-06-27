package org.codelab.graacc.Usuarios.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorld {

    @Operation(summary = "Hello World.", description = "Não é obrigatório uso de token de autenticação.")
    @GetMapping
    ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World from API-Usuarios");
    }

}
