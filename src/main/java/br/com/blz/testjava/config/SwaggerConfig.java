package br.com.blz.testjava.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;


@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {
    public static final String SWAGGER_TITULO = "Produtos na Beleza na Web";

    public static final String SWAGGER_DESCRICAO = "Recursos referentes a produtos oferecidos pela Beleza na Web";

    @Value("${springfox.documentation.swagger.v2.home}")
    private String home;

    @Autowired
    private Docket dock;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        dock.apiInfo(apiInfo());

        registry.addResourceHandler(home + "/**").addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @SuppressWarnings("deprecation")
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
            "REST API",
            "Fornece os metodos responsáveis para gerenciar os produtos da empresa Beleza na Web.",
            "Versão API 1.0",
            "Termos de uso",
            "brunosaar.s@gmail.com",
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0"
        );
        return apiInfo;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.blz.testjava"))
            .paths(PathSelectors.any())
            .build()
            .globalOperationParameters(Arrays.asList(authParam()))
            ;
    }

    private Parameter authParam() {
        return new ParameterBuilder()
            .name("Authorization")
            .description("Token ...")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .required(false)
            .build();

    }
}
