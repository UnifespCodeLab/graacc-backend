package org.codelab.graacc.Orquestrador;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrquestradorApplication {

	public static Dotenv carregaVariaveisAmbiente() {
		return Dotenv.configure()
				.directory("./")
				.filename(".env")
				.ignoreIfMalformed()
				.ignoreIfMissing()
				.systemProperties()
				.load();
	}

	public static void main(String[] args) {
		carregaVariaveisAmbiente();
		SpringApplication.run(OrquestradorApplication.class, args);
	}

}
