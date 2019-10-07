package br.com.blz.testjava.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * Classe de configuracao do Swagger.
 */
@Configuration
@EnableSwagger2
 class SwaggerConfig {

    /**
     * Metodo de definicao dos paths para
     * documentacao.
     * @return Docket
     * @see Docket
     */
    @Bean
    Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
    }
}
