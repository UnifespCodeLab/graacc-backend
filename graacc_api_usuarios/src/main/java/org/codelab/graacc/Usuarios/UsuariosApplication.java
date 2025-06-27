package org.codelab.graacc.Usuarios;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UsuariosApplication {

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
		Dotenv dotenv = carregaVariaveisAmbiente();
		SpringApplication.run(UsuariosApplication.class, args);
	}

}
