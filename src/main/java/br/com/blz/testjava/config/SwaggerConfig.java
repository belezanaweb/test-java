package br.com.blz.testjava.config;

import io.swagger.annotations.ApiParam;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

import lombok.Getter;

@Profile("!test")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.blz.testjava.controller"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo())
            .directModelSubstitute(Pageable.class, SwaggerPageable.class);
    }

    private static ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Beleza na Web - Test Java")
            .description("API MVC Restfull with Maven, JAVA 11 and SpringBoot 2.4.0")
            .version("1.0")
            .contact(new Contact("Marcus Voltolim", "https://github.com/marcusvoltolim", "marcus.voltolim@gmail.com"))
            .build();
    }

    @Getter
    private static class SwaggerPageable {

        @ApiParam(value = "Number of records per page", example = "0")
        @Nullable
        private Integer size;

        @ApiParam(value = "Results page you want to retrieve (0..N)", example = "0")
        @Nullable
        private Integer page;

        @ApiParam(value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")
        @Nullable
        private String sort;

    }

}
