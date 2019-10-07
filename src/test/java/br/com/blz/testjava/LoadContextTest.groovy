package br.com.blz.testjava

import br.com.blz.testjava.controller.ProductsApiController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class LoadContextTest extends Specification {

    @Autowired (required = false)
    private ProductsApiController controller

    def "when context is loaded then all expected beans are created"() {
        expect: "the ProductsApiController is created"
        controller
    }
}
