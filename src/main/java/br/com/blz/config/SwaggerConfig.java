package br.com.blz.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
	
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.blz.controllers"))
                .paths(PathSelectors.any())
                .build()
                .produces(producers())
                .consumes(consumers())
                .apiInfo(metaData());
    }
	
	private Set<String> consumers(){
    	Set<String> set = new HashSet<String>();
    	set.add("application/json");
    	return set;
    }
    
    private Set<String> producers(){
    	Set<String> set = new HashSet<String>();
    	set.add("application/json");
    	return set;
    }
    
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Beleza na Web API Documentação")
                .version("1.0")
                .contact(new Contact("Danilo Santos", "", "santos.danilo@outlook.com"))
                .description("Este é um serviço REST do Beleza na Web")
                .build();
    }
    
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
