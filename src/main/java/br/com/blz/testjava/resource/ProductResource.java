package br.com.blz.testjava.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.dto.ProductDTO;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping(path = "/product")
public class ProductResource {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
	
	@PostMapping
	public ResponseEntity<Product> criar(@Valid @RequestBody ProductDTO dto){
		
		Product saveProduct = productService.create(dto);
		
	    return ResponseEntity.status(HttpStatus.CREATED).body(saveProduct);
		
	}
	
	@PutMapping("/{sku}")
    public final ResponseEntity<Object> updateProduct(@PathVariable Long sku, @RequestBody Product product) {

		productService.updateProduct(sku, product);

        return ResponseEntity.accepted().body("Produto atualizado");
    }
	
    @GetMapping("/{sku}")
    public final ResponseEntity<Object> searchProduct(@PathVariable Long sku) {
	 	Product product = productService.searchProduct(sku);
	 
        return ResponseEntity.ok().body(product);
    }
    
    @DeleteMapping("/{sku}")
    public final ResponseEntity<Object> deleteProduct(@PathVariable Long sku) {

    	productRepository.deleteById(sku);

        return ResponseEntity.accepted().body("Produto "+sku+" deletado.");
    }
}
