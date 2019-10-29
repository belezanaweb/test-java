package br.com.blz.testjava.resource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import br.com.blz.testjava.dao.entity.Product;
import br.com.blz.testjava.service.IProductService;
import io.swagger.annotations.Api;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(value="Product API")
@RequestMapping("/api/products")
public class ProductResource {

	@Autowired
    private IProductService productService;
	
    @GetMapping
    public List<Product> findAll() {
        Iterable<Product> products = productService.findAll();
        return StreamSupport.stream(products.spliterator(), false)
                .collect(Collectors.toList());
    }
    
    @GetMapping(value = "/{sku}")
    public Product find(@PathVariable("sku") Long sku) {
    	return productService.findById(sku);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@Valid @RequestBody Product product, @ApiIgnore WebRequest webRequest) {
    	webRequest.setAttribute("product", product, RequestAttributes.SCOPE_REQUEST);
        Product productPostCreated = productService.create(product);
        return productPostCreated;
    }
    
    @PutMapping(value = "/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public Product update(@PathVariable("sku") Long sku, @Valid @RequestBody Product product, @ApiIgnore WebRequest webRequest) {
    	webRequest.setAttribute("product", product, RequestAttributes.SCOPE_REQUEST);
        Product productPostCreated = productService.update(sku, product);
        return productPostCreated;
    }

    @DeleteMapping(value = "/{sku}")
    public void delete(@PathVariable("sku") Long sku) {
       productService.delete(sku);
    }
}