package br.com.blz.testjava.resource;

import br.com.blz.testjava.business.ProductBusiness;
import br.com.blz.testjava.exception.ProductAlreadyExistException;
import br.com.blz.testjava.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ProductResource {

    @Autowired
    private ProductBusiness productBusiness;

    @PostMapping("/product")
    public ResponseEntity save(@RequestBody Product product){
        try {
            Product productNew = productBusiness.save(product);
            return ResponseEntity.ok().body(productNew);
        } catch (ProductAlreadyExistException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/product/{sku}")
    public ResponseEntity get(@PathVariable(name = "sku") Long sku){
        Product product = productBusiness.getBySku(sku);
        if(product != null){
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/product/{sku}")
    public ResponseEntity delete(@PathVariable(name = "sku") Long sku){
        productBusiness.delete(sku);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/product/{sku}")
    public ResponseEntity update(@PathVariable(name = "sku") Long sku, @RequestBody Product product){
        Product update = productBusiness.update(sku, product);
        return ResponseEntity.ok().body(update);
    }
}
