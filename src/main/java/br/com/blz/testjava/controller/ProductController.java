package br.com.blz.testjava.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.blz.testjava.controller.mapper.ProductMapper;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.interfaces.IProductService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

	@Autowired
	private IProductService service;
	
	Product product = null;

	@ApiOperation(value = "Cadastra produtos.")
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody String req) throws JsonProcessingException, IOException {
		log.info("Create product.");
		if(req != null) {
			product = new ProductMapper(req).product();
		}
		
		if (product == null) {
			return new ResponseEntity<String>("Produto não encontrado.", HttpStatus.BAD_REQUEST);
		}
		
		if (service.isExists(product.getSku())) {
			return new ResponseEntity<String>("Produto já cadastrado.", HttpStatus.BAD_REQUEST);
		}
		
		service.create(product);
		return ResponseEntity.ok("Produto cadastrado com sucesso.");
	}

	@ApiOperation(value = "Alterar produtos.")
	@PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody String req) throws JsonProcessingException, IOException {
		log.info("Update product.");
		if(req != null) {
			product = new ProductMapper(req).product();
		}
		
		if (product == null) {
			return new ResponseEntity<String>("Produto não encontrado.", HttpStatus.BAD_REQUEST);
		}
		
		service.create(product);
		return ResponseEntity.ok("Produto alterado com sucesso.");
	}

	@ApiOperation(value = "Excluir produtos.")
	@DeleteMapping(value = "/delete/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable(name = "sku", required = false) Long sku) {
		log.info("Delete product.");
		
		if (sku == null) {
			return new ResponseEntity<String>("Sku não encontrado.", HttpStatus.BAD_REQUEST);
		}
		
		service.delete(Long.valueOf(sku));
		return ResponseEntity.ok("Produto excluído com sucesso.");
	}

	@ApiOperation(value = "Ler produto por sku.")
	@GetMapping(value = "/reader/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> reader(@PathVariable(name = "sku", required = false) Long sku) {
		log.info("Reader product.");
		
		if (sku == null) {
			return new ResponseEntity<String>("Sku não encontrado.", HttpStatus.BAD_REQUEST);
		}
		
		return ResponseEntity.ok(service.reader(Long.valueOf(sku)));
	}
}