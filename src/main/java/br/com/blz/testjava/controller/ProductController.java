package br.com.blz.testjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.domain.Product;
import br.com.blz.testjava.exception.BusinessException;
import br.com.blz.testjava.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(method = RequestMethod.POST)	
	public ResponseEntity<?> insertProduct(@RequestBody Product product) throws Exception {
		
		productService.insert(product);
		Object body = "Produto criado com sucesso!";
		return new ResponseEntity<Object>(body, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Object>  updateProduct(@RequestBody Product product) throws BusinessException {
		
		productService.update(product);
		Object body = "Produto atualizado com sucesso!";
		return new ResponseEntity<Object>(body, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/sku/{sku}",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> findProductBySku(@PathVariable Long sku) {
		
		Product product = productService.recoverProductBySku(sku);		
		
		if(product != null) {
			return ResponseEntity.ok().body(product);
		}else {
			Object body = "Produto não encontrado!";
			return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value = "/sku/{sku}",method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduct(@PathVariable Long sku) {
		
		  if(productService.delete(sku)) {
			  Object body = "Produto removido com sucesso!";
				return new ResponseEntity<Object>(body, HttpStatus.OK);
		  }else {
			  Object body = "Produto não encontrado!";
			  return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
		  }
	}


}
