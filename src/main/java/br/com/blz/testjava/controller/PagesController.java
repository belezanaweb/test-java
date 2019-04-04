package br.com.blz.testjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.dto.Product;
import br.com.blz.testjava.service.PagesService;

@RestController
public class PagesController {

	@Autowired
	private PagesService service;

	@PostMapping("/create")
	public Product create(@RequestBody Product product) {
		service.create(product);
		return product;
	}

	@PutMapping("/edit")
	public Product edit(@RequestParam(value = "sku") Long sku, @RequestBody Product product) {
		service.edit(sku, product);
		return product;
	}

	@GetMapping("/find")
	public Product find(@RequestParam(value = "sku") Long sku) {
		return service.find(sku);
	}

	@DeleteMapping(value = "/delete")
	public Product delete(@RequestParam(value = "sku") Long sku) {
		return service.delete(sku);
	}
}
