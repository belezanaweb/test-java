package br.com.blz.testjava.product;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.model.Product;

@RestController
@RequestMapping(path = "product")
final class ProductController {

	private final ProductService productService;

	@Autowired
	public ProductController(final ProductService productService) {
		this.productService = productService; 
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product createProduct(@RequestBody @NotNull final Product product) {
		return productService.create(product);
	}

	@RequestMapping(path = "/update/{sku}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product updateProduct(@PathVariable final Integer sku, @RequestBody final Product product) {
		return productService.update(sku, product);
	}

	@RequestMapping(path = "/delete/{sku}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteProduct(@PathVariable final Integer sku) {
		return productService.delete(sku);
	}

	@RequestMapping(value = "/find/{sku}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product findProduct(@PathVariable final Integer sku) {
		return productService.find(sku);
	}

}
