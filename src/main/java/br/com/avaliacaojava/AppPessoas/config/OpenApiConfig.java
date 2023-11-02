package br.com.avaliacaojava.AppPessoas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components().addSecuritySchemes("basicScheme",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
				.info(new Info()
						.title("App Cadastro de pessoas e contatos")
						.description("Este aplicativo faz controle de cadastro de pessoas e contatos")
						.contact(new Contact().name("Nome").email("email").url("url"))
						.version("Versão 0.0.1-SNAPSHOT"));
	}
	
	
}