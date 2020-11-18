package br.com.blz.testjava.resource;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.blz.testjava.business.domain.GlobalMessage;
import br.com.blz.testjava.business.domain.Product;
import br.com.blz.testjava.business.service.ProductService;
import br.com.blz.testjava.dto.ProductDto;
import br.com.blz.testjava.dto.Response;
import br.com.blz.testjava.dto.Response.Message;
import br.com.blz.testjava.mapper.ProductMapper;

@Controller
@RequestMapping("/api/v1/produtos")
public class ProductResource {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response<ProductDto>> save(@RequestBody ProductDto product) {
		
		Product currentProduct = productService.save(ProductMapper.toEntity(product));
		
		Response<ProductDto> response = Response.<ProductDto>builder()
				.result(ProductMapper.toDto(currentProduct))
				.messages(Arrays.asList(Message.builder()
						.code(GlobalMessage.CREATED.getCode())
						.description(GlobalMessage.CREATED.getDescription())
						.build()))
				.build();
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(response);
	}
	
	@PutMapping(value = "/{sku}")
	public ResponseEntity<Response<ProductDto>> update(@RequestBody ProductDto product, @PathVariable("sku") Long sku) {
		
		Product currentProduct = productService.update(ProductMapper.toEntity(product), sku);
		
		Response<ProductDto> response = Response.<ProductDto>builder()
				.result(ProductMapper.toDto(currentProduct))
				.messages(Arrays.asList(Message.builder()
						.code(GlobalMessage.SUCCESS.getCode())
						.description(GlobalMessage.SUCCESS.getDescription())
						.build()))
				.build();
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(response);
	}
	
	@GetMapping(path = "/{sku}")
	public ResponseEntity<Response<ProductDto>> find(@PathVariable(name = "sku", required = true) Long sku) {
		Optional<Product> currentProduct = productService.findBySku(sku);
			
		if (!currentProduct.isPresent()) {
			Response<ProductDto> response = Response.<ProductDto>builder()
					.messages(Arrays.asList(Message.builder()
							.code(GlobalMessage.NOT_CONTENT.getCode())
							.description(GlobalMessage.NOT_CONTENT.getDescription())
							.build()))
					.build();
			
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(response);
		}

		Response<ProductDto> response = Response.<ProductDto>builder().
				result(ProductMapper.toDto(currentProduct.get()))
				.messages(Arrays.asList(Message.builder()
						.code(GlobalMessage.SUCCESS.getCode())
						.description(GlobalMessage.SUCCESS.getDescription())
						.build()))
				.build();

		return ResponseEntity.status(HttpStatus.OK)
				.body(response);
	}
	
	@DeleteMapping(value = "/{sku}")
	public ResponseEntity<?> delete(@PathVariable(name = "sku", required = true) Long sku) {
		Optional<Product> currentProduct = productService.findBySku(sku);
		
		if (!currentProduct.isPresent()) {
			Response<ProductDto> response = Response.<ProductDto>builder()
					.messages(Arrays.asList(Message.builder()
							.code(GlobalMessage.NOT_CONTENT.getCode())
							.description(GlobalMessage.NOT_CONTENT.getDescription())
							.build()))
					.build();
			
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(response);
		}
		
		productService.deleteBySku(sku);
		
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.build();
	}
}
