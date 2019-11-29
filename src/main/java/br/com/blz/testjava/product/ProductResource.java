package br.com.blz.testjava.product;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.commons.exception.BindingException;
import br.com.blz.testjava.commons.exception.NotFoundException;

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
	
	@PostMapping
	public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO data, BindingResult results) {
		if(results.hasErrors()) {
			throw new BindingException(results);
		}
		Product product = this.productService.save(data.parse());
		return ResponseEntity.status(HttpStatus.CREATED).body(ProductDTO.from(product));
	}
	
	@PutMapping("/{sku}") 
	public ResponseEntity<ProductDTO> update(@PathVariable Long sku, @Valid @RequestBody ProductDTO data, BindingResult results){
		if(results.hasErrors()) {
			throw new BindingException(results);
		}

		if(!this.productService.hasBySku(sku)) {
			throw new NotFoundException();
		}

		Product newProduct = this.productService.update(Product.builder()
				.sku(data.getSku())
				.name(data.getName())
				.inventory(data.getInventoryDTO().parse())
				.isMarketable(data.getIsMarketable())
				.build());

		ProductDTO productDTO = ProductDTO.from(newProduct);
		return ResponseEntity.ok(productDTO);
	}
	
	@DeleteMapping("/{sku}")
	public void delete(@PathVariable Long sku) {
		if(!this.productService.hasBySku(sku)) {
			throw new NotFoundException();
		}
		
		this.productService.delete(sku);
	}
	
}
