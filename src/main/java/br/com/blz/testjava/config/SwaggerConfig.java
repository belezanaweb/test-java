package br.com.blz.testjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public static final String RESOURCE_HANDLER = "swagger-ui.html";
	public static final String TITLE = "Ecommerce";
	public static final String DESCRIPTION = "Details about Ecommerce API";
	
	@Bean
	public Docket apiConfigDocs() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.blz.testjava"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(informacoesAPI());
}

	private ApiInfo informacoesAPI() {
	   return new ApiInfoBuilder()
	           .title(TITLE)
	           .description(DESCRIPTION)
	           .build();
	}
	
}
