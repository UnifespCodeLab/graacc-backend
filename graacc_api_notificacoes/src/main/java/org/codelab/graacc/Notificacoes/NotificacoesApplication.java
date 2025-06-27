package org.codelab.graacc.Notificacoes;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificacoesApplication {

	private static Dotenv carregaVariaveisAmbiente() {
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
		SpringApplication.run(NotificacoesApplication.class, args);
	}

}
