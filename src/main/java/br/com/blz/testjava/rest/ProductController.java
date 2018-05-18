package br.com.blz.testjava.rest;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("product")
final class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(path = "/saveOrUpdate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Integer saveOrUpdate(@RequestBody @NotNull final Product product) {
		return productService.saveOrUpdate(product);
	}

	@RequestMapping(path = "/findBySku/{sku}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product findBySku(@PathVariable final Integer sku) {
		return productService.findBySku(sku);
	}

	@RequestMapping(path = "/delete/{sku}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product delete(@PathVariable final Integer sku) {
		return productService.delete(sku);
	}
}
