package br.com.blz.testjava.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.swagger.web.ApiResourceController;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;


@Controller
@ApiIgnore
@RequestMapping(value = "${springfox.documentation.swagger.v2.home}" + "/swagger-resources")
public class CustomHomeApiResourceController extends ApiResourceController {

    public CustomHomeApiResourceController(SwaggerResourcesProvider swaggerResources) {
        super(swaggerResources);
    }

}
