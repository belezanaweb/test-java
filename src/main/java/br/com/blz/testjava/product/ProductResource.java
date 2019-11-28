package br.com.blz.testjava.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teste-java/product")
public class ProductResource {
	
	@Autowired 
	private ProductServiceImpl productService;

	@GetMapping("/{sku}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long sku){
		Product product = this.productService.findBySku(sku);
		ProductDTO productDTO = ProductDTO.from(product);
		return ResponseEntity.ok(productDTO);
	}
	
}
