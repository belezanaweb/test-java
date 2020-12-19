package br.com.blz.testjava.api.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.web.context.request.ServletWebRequest;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration{

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("br.com.blz.testjava.api.controllers"))
                    .build()
                .apiInfo(apiInfo())
                .ignoredParameterTypes(URI.class, URL.class, URLStreamHandler.class, Resource.class, File.class, ServletWebRequest.class, InputStream.class, Sort.class)
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Beleza na Web - Test Java")
                .description("API desenvolvida no processo seletivo para a vaga backend Java.")
                .version("0.0.1")
                .license("GPL")
                .contact(new Contact("Thiano Pereira Lima",
                        "https://github.com/belezanaweb/test-java",
                        "thianolima@hotmail.com"))
                .build();
    }
}
