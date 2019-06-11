package br.com.blz.testjava.product;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.blz.testjava.error.ProductSavedException;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@PostMapping("/add")
	@ExceptionHandler({ ProductSavedException.class })
	@ResponseBody
	public Product saveProduct(@RequestBody @Valid Product product) throws ProductSavedException {
		return service.saveProduct(product);
	}
}
