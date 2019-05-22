package br.com.blz.testjava.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductResource {

	@GetMapping
	public List<String> all() {
		return Arrays.asList("Condicionador");
	}

}
