package br.com.beleza.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
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
public class SwaggerConfig {

	@Bean
	public Docket api() {
		
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(apis())
				.paths(PathSelectors.any())
				.build().apiInfo(getApiInfoCustom());
	}
	
	  private Predicate<RequestHandler> apis() {
	        return RequestHandlerSelectors.basePackage("br.com.beleza");
	  }
	
	    private ApiInfo getApiInfoCustom() {

	        return new ApiInfoBuilder().title("Beleza na Web Info Api")
	            .description("Product Rest API")
	            .version("1.0.0")
	            .contact(new Contact("Fagner Pinho", "", "fagnersp.ti@gmail.com"))
	            .license("Apache 2.0")
	            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
	            .build();

	    }
}
