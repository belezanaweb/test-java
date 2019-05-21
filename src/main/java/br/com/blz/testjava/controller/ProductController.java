package br.com.blz.testjava.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.dto.ProductsDTO;
import br.com.blz.testjava.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	private final ProductService service;
	
	public ProductController(ProductService service) {
		this.service = service;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Cria um produto")
	public Product create(@ApiParam(value = "Cliente", required = true) @RequestBody @Valid ProductsDTO product) {
		return service.saveProduct(product);
	}
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	@ApiOperation(value = "Trás todas os produtos")
	public Iterable<Product> getAll() {
		return service.getAll();
	}
	
	@RequestMapping(value = "/{sku}", method = RequestMethod.GET)
	@ApiOperation(value = "Encontra um Produto pelo Sku")
	public Product find(@ApiParam(value = "Sku do produto", required = true) @PathVariable Long sku) {
		return service.findBySku(sku);

	}

}
