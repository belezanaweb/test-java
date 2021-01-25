package br.com.blz.testjava.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {

	
	private static Logger LOG = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void create(@RequestBody ProductDTO produtoDto) {
		LOG.info("Produto cadastro recebido");
		Product product = convertToEntity (produtoDto);
		 productService.create(product);
	}
	
	@GetMapping ("/{id}")
	public ProductDTO recupera(@PathVariable Long id) {
		LOG.info("Recuperando cadastro produto");
		Product prod =  productService.find(id);
		return convertToDto(prod);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void update(  @PathVariable(value = "id") Long sku, @RequestBody ProductDTO produtoDto) {
		LOG.info("Alteração de produto recebida");
		produtoDto.setSku(sku);
		Product product = convertToEntity (produtoDto);
		 productService.update(product);
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(  @PathVariable(value = "id") Long sku) {
		LOG.info("Remo~]ap de produto recebida");
		 productService.delete(sku);
	}
	
	
	
	private Product convertToEntity(ProductDTO productDto)  {
	    Product product = modelMapper.map(productDto, Product.class);
	    
	    return product;
	}
	
	private ProductDTO convertToDto(Product product) {
		ProductDTO productDto = modelMapper.map(product, ProductDTO.class);
	    return productDto;
	}
	
}
