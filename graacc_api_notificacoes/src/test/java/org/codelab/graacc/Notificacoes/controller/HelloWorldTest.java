package org.codelab.graacc.Notificacoes.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
@DisplayName("HelloWorldTest")
class HelloWorldTest {

    @InjectMocks
    HelloWorld helloController;

    @Test
    @DisplayName("Hello World Endpoint")
    void helloWorldEndpoint() {
        var resposta = helloController.hello();
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals("Hello World from API-Notificacoes", resposta.getBody());
    }

}
