package org.codelab.graacc.Agendamentos.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@Tag(name = "Hello Controller")
public class HelloController {

    @GetMapping
    ResponseEntity hello() {
        return ResponseEntity.ok().body("Hello World from API-Agendamentos");
    }

}
