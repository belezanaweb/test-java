package br.com.blz.testjava.controller;

import br.com.blz.testjava.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
	private final ProductService productService;

	ProductController(final ProductService productService) {
	    this.productService = productService;
    }
}
