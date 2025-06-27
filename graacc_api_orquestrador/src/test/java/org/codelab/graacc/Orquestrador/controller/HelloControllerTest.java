package org.codelab.graacc.Orquestrador.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

@SpringBootTest
public class HelloControllerTest {

    @InjectMocks
    HelloController helloController;

    @Test
    @DisplayName("Hello World Endpoint")
    void helloWorldEndpoint() {
        var resposta = helloController.hello();
        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
        Assertions.assertEquals("Hello World from API-Orquestrador", resposta.getBody());
    }
}
