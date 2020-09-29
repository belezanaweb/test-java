package br.com.blz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.dto.ProductDTO;
import br.com.blz.exception.BusinessException;
import br.com.blz.service.ProductService;



@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
//	@PostMapping("/product")
//	public ResponseEntity<Product> postMapping(@RequestBody Product product) throws Exception {
//		 productService.save(product);
//		 return new ResponseEntity<Product>(product,HttpStatus.CREATED);
//	}
	@PostMapping("/product")
	public ResponseEntity<ProductDTO> post(@RequestBody ProductDTO product) throws Exception {
		productService.save(product);
		return new ResponseEntity<ProductDTO>(product,HttpStatus.CREATED);
	}
	
	 @GetMapping("/{id}")
	 public ResponseEntity<ProductDTO> getProduct(@RequestParam(value = "id") Long id) throws Exception {
		 ProductDTO product = productService.findId(id);
		 return new ResponseEntity<ProductDTO>(product,HttpStatus.OK);
	 }
	 
	 @PutMapping("/{id}")
	 public ProductDTO getEdit(@RequestBody ProductDTO productDTO) throws BusinessException {
		 productService.edit(productDTO);
		 return productDTO;
	 }
	 
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Boolean> delete(@RequestParam(value = "id") Long id) {
		 productService.deleteById(id);
		 return new ResponseEntity<Boolean>(Boolean.TRUE,HttpStatus.OK);
	 }
}
