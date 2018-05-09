package br.com.blz.testjava.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.StockService;

@RestController
@RequestMapping(value = "/stock")
public class StockController {
	
	@Autowired
	StockService stockService;
	
	 @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE  )
	 @ResponseBody
	 public ResponseEntity<String> stockAdd(@RequestBody Product product) throws Exception {
		 
		 HttpStatus httpStatus;
		 
		 httpStatus = stockService.add(product);
		 
		 return new ResponseEntity<String>(httpStatus);
		 
	 }

	 @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	 @ResponseBody
	 public List<Product> stockList() throws Exception {
		 List<Product> productList = new ArrayList<Product>();
		 
		 productList = stockService.getList();
		 
		 return productList;
	 }

	 @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE  )
	 @ResponseBody
	 public ResponseEntity<String> stockUpdate(@RequestBody Product product) throws Exception {
		 
		 HttpStatus httpStatus;
		 
		 httpStatus = stockService.update(product);
		 
		 return new ResponseEntity<String>(httpStatus);
	 }

	 @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE  )
	 @ResponseBody
	 public ResponseEntity<String> stockDelete (@RequestBody Product product) throws Exception {
		 HttpStatus httpStatus;
		 
		 httpStatus = stockService.delete(product);
		 
		 return new ResponseEntity<String>(httpStatus);
		 
	 }
}
