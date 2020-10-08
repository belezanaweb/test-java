package br.com.blz.testjava.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.regex("/actuator.*||/error.*").negate())                          
          .build();                                           
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry
      	.addResourceHandler("/swagger-ui/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
        .resourceChain(false);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/swagger-ui")
          .setViewName("forward:" + "/swagger-ui/index.html");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry
          .addMapping("/v2/api-docs.*")
          .allowedOrigins("http://editor.swagger.io");
    }
}