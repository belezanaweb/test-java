package br.com.blz.testjava.resource;

import br.com.blz.testjava.business.ProductBusiness;
import br.com.blz.testjava.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ProductResource {

    @Autowired
    private ProductBusiness productBusiness;

    @PostMapping("/produto")
    public ResponseEntity save(@RequestBody Product product){
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/produto/{sku}")
    public ResponseEntity get(@PathVariable(name = "sku") Long sku){
        Product product = productBusiness.getBySku(sku);
        if(product != null){
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/produto/{sku}")
    public ResponseEntity delete(@PathVariable(name = "sku") Long sku){
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PutMapping("/produto/{sku}")
    public ResponseEntity update(@PathVariable(name = "sku") Long sku, @RequestBody Product product){
        return ResponseEntity.ok(new ArrayList<>());
    }

}
