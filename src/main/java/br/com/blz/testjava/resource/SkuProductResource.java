package br.com.blz.testjava.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.model.SkuProduct;
import br.com.blz.testjava.service.SkuProductService;

@RestController
@RequestMapping("/sku/product")
public class SkuProductResource {
	@Autowired
	SkuProductService skuProductService;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createProduct(@RequestBody final SkuProduct skuProduct) {
		return skuProductService.create(skuProduct);
	}

	@GetMapping
	@ResponseBody
	public List<SkuProduct> list() {

		return skuProductService.listskuProduct();
	}

	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> delete(@RequestBody final SkuProduct skuProduct) {

		return skuProductService.delete(skuProduct);
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> update(@RequestBody final SkuProduct skuProduct) {
		return skuProductService.updateProduct(skuProduct);
	}

}
