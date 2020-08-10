package br.com.blz.produtos.apirest.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.blz.produtos"))
				.paths(PathSelectors.regex("/api.*"))
				.build()
				.apiInfo(metaInfo());
	}
	
	private ApiInfo metaInfo() {
		@SuppressWarnings("rawtypes")
		ApiInfo apiInfo = new ApiInfo(
			"Produtos API REST",
			"API REST para Cadatro de produtos",
			"1.0",
			"Terms of Service",
			new Contact("Danhylo Ramos", "https://br.linkedin.com/in/danhylo-almeida-ramos-980090139", "danhyloramos@gmail.com"),
			"Apache License Version 2.0",
			"https://www.apache.org/licensen.html", new ArrayList<VendorExtension>()
		);
		
		return apiInfo;
	}
	

}
