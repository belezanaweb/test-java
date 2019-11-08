package br.com.blz.testjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.dto.Produto;
import br.com.blz.testjava.service.ProdutoService;

@RestController	
@RequestMapping(value = "/v1/produtos-app")
public class ProdutoController {

	private final ProdutoService service;
	

	@Autowired
	ProdutoController(ProdutoService service) {
		this.service = service;
	}


	@RequestMapping(value = "/busca-produto/{sku}", method = RequestMethod.GET)
	@ResponseBody
	Produto findBySku(@PathVariable("sku") String sku) {
		return service.findBySku(sku);
	}
	
	@RequestMapping(value = "/criar-produto", method = RequestMethod.PUT, produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	ResponseEntity<?> create(@RequestBody Produto produto) {
		try {
			service.create(produto);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}

	  	return ResponseEntity.ok(produto);
	}

	@RequestMapping(value = "/remover-produto/{sku}", method = RequestMethod.DELETE)
	@ResponseBody
	ResponseEntity<?> delete(@PathVariable("sku") String sku) {
		service.delete(sku);
		return ResponseEntity.noContent().build();
	}


	
	@RequestMapping(value = "/atualizar-produto/{sku}", method = RequestMethod.POST)
	@ResponseBody
	ResponseEntity<?>  update(@RequestBody Produto produto, @PathVariable("sku") String sku) {
		
		try {
			service.update(produto);
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
			
		}
	
		return ResponseEntity.ok(service.findBySku(sku));
	
	}

}
