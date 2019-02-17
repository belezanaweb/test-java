package br.com.blz.testjava.api;

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
import br.com.blz.testjava.service.ProductStockService;
import br.com.blz.testjava.view.ProductStockView;

@RestController
@RequestMapping(value = "/product")
public class ProductStockAPI {
	
	@Autowired
	ProductStockService stockService;
	
	 @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE  )
	 @ResponseBody
	 public ResponseEntity<String> stockAdd(@RequestBody Product product) throws Exception {
		 
		 HttpStatus httpStatus = HttpStatus.OK;
		 Boolean resultCall;
		 
		 resultCall = stockService.add(product);
		 
		 if (!resultCall) {
			 httpStatus = HttpStatus.BAD_REQUEST;
		 }
		 
		 return new ResponseEntity<String>(httpStatus);
		 
	 }

	 @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	 @ResponseBody
	 public List<ProductStockView> stockListView() throws Exception {
		 List<ProductStockView> productListView = new ArrayList<ProductStockView>();
		 
		 productListView = stockService.getList();
		 
		 return productListView;
	 }

	 @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE  )
	 @ResponseBody
	 public ResponseEntity<String> stockUpdate(@RequestBody Product product) throws Exception {
		 
		 HttpStatus httpStatus = HttpStatus.OK;
		 Boolean resultCall;
		 
		 resultCall = stockService.update(product);
		 
		 if (!resultCall) {
			 httpStatus = HttpStatus.BAD_REQUEST;
		 }
		 
		 return new ResponseEntity<String>(httpStatus);
	 }

	 @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE  )
	 @ResponseBody
	 public ResponseEntity<String> stockDelete (@RequestBody Product product) throws Exception {
		 HttpStatus httpStatus = HttpStatus.OK;
		 Boolean resultCall;
		 
		 resultCall = stockService.delete(product);
		 
		 if (!resultCall) {
			 httpStatus = HttpStatus.BAD_REQUEST;
		 }
		 
		 return new ResponseEntity<String>(httpStatus);
		 
	 }
}