package br.com.blz.testjava.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class CustomHomeController {

    @Value("${springfox.documentation.swagger.v2.home}")
    private String home;

    @GetMapping(value = "${springfox.documentation.swagger.v2.home}")
    public String index() {
        return "redirect:" + home + "/swagger-ui.html";
    }
}
