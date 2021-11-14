package br.com.jwt.authenticaton.jwtauthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**Dado o exclude para que o security não obrigue a utilização se login e senha para as chamadas rest, que por padrão o login: 'user' e a senha: é gerada pelo security
 * e exibida no console ao executar a aplicação*/
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class JwtAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthenticationApplication.class, args);
	}

}
