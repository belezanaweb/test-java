package br.com.blz.testjava.controller;

import br.com.blz.testjava.exception.AlreadyExistsException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.ProductResponse;
import br.com.blz.testjava.service.ProductInventoryService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductInventoryService service;

    @GetMapping("/products/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse get(@PathVariable("sku") Integer id) {
        return  service.findProduct(id);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Product product) throws AlreadyExistsException {
         service.save(product);
    }

     @PutMapping("/products/{sku}")
     @ResponseStatus(HttpStatus.NO_CONTENT)
     public void update(@PathVariable("sku") Integer id,@RequestBody Product product) {
        service.update(id,product);
    }

    @DeleteMapping("/products/{sku}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("sku") Integer id) {
        var productRemove = get(id);
        service.remove(productRemove);
    }
}
