package org.codelab.graacc.Usuarios.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
@DisplayName("HelloWorldTest")
public class HelloWorldTest {
    @InjectMocks
    HelloWorld helloWorld;

    @Test
    @DisplayName("Hello World Endpoint")
    void helloWorldEndpoint() {
        var resposta = helloWorld.hello();
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals("Hello World from API-Usuarios", resposta.getBody());
    }
}
