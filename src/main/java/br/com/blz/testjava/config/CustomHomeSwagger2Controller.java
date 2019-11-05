package br.com.blz.testjava.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.json.JsonSerializer;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;
import springfox.documentation.swagger2.web.Swagger2Controller;

@Controller
@ApiIgnore
@RequestMapping(value = "${springfox.documentation.swagger.v2.home}")
public class CustomHomeSwagger2Controller extends Swagger2Controller {

    @Autowired
    public CustomHomeSwagger2Controller(Environment environment, DocumentationCache documentationCache,
                                        ServiceModelToSwagger2Mapper mapper, JsonSerializer jsonSerializer) {
        super(environment, documentationCache, mapper, jsonSerializer);

    }
}

