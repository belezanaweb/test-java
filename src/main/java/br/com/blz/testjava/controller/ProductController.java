package br.com.blz.testjava.controller;

import br.com.blz.testjava.controller.support.CrudController;
import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.service.ProductService;
import util.Constants;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(Constants.API_V_1 + "/product")
@RestController
public class ProductController extends CrudController<ProductService, Long, ProductDto> {

    public ProductController(ProductService service) {
        super(service);
    }

}
