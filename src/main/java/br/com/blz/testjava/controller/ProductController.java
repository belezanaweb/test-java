package br.com.blz.testjava.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService service;

	@RequestMapping(path = "/product/{sku}", method = RequestMethod.GET)
	public @ResponseBody ProductDTO getProduct(@PathVariable int sku, HttpServletResponse response) {
		ProductDTO dto = service.getProduct(sku);
		if (dto == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
		return dto;
	}

	@RequestMapping(path = "/product", method = RequestMethod.PUT)
	public @ResponseBody ProductDTO includeProduct(@RequestBody ProductDTO product, HttpServletResponse response) throws Exception {
		try {
			
			ProductDTO pd = service.createProduct(product);
			response.setStatus(HttpServletResponse.SC_CREATED);
		} catch (Exception e) {
			// Caso um produto já existente em memória tente ser criado com o mesmo sku uma exceção deverá ser lançada
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			throw e;
		}
		return null;
	}

	@RequestMapping(path = "/product", method = RequestMethod.POST)
	public @ResponseBody ProductDTO updateProduct(@RequestBody ProductDTO product, HttpServletResponse response) {
		ProductDTO pd = service.updateProduct(product);
		if (pd == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
		return pd;
	}

	@RequestMapping(path = "/product/{sku}", method = RequestMethod.DELETE)
	public @ResponseBody ProductDTO deleteProduct(@PathVariable int sku) {
		return service.removeProduct(sku);
	}

}
