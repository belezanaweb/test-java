package br.com.blz.api.config;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {

		Docket docket = new Docket(DocumentationType.SWAGGER_2);

		docket		
			.select()
	        .apis(RequestHandlerSelectors.basePackage("br.com.blz"))
	        .paths(regex("/api.*"))
	        .build()
	        .apiInfo(this.informacoesApi().build());
		
		return docket;
	}

	private ApiInfoBuilder informacoesApi() {

		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

		apiInfoBuilder.title("API - BelezaNaWeb");
		apiInfoBuilder.description("Api para realização do Testes Beleza na Web.");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.termsOfServiceUrl("Termo de uso: Deve ser usada para o teste Java");
		apiInfoBuilder.contact(this.contato());

		return apiInfoBuilder;

	}

	private Contact contato() {

		return new Contact("Renato Zacanti", "https://www.linkedin.com/in/renato-zacanti-85479776/", "rzacanti@gmail.com");
	}

}