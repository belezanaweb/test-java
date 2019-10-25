package br.com.blz.testjava.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	 @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("br.com.blz"))
	                .paths(PathSelectors.any())
	                .build()
	                .apiInfo(getApiInfo());
	    }

	    private ApiInfo getApiInfo() {
	        return new ApiInfo(
	                "Esta é uma avaliação básica de código. "
	                + "O objetivo é conhecer um pouco do seu conhecimento/prática de RESTful, Spring e Java.",
	                "Serviço para Gerenciamento de Estoque",
	                "1.0.0",
	                "",
	                new Contact("", "", ""),
	                "Beleza na Web",
	                "https://www.belezanaweb.com.br",
	                Collections.emptyList());
	    }	
}
