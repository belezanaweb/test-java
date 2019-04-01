package br.com.blz.testjava.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.business.interfaces.IProductService;
import br.com.blz.testjava.model.dto.ProductDTO;
import br.com.blz.testjava.model.vo.ProductVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "Product", tags = { "PRODUCT" })
public class ProductController {

	private static final Logger logger = Logger.getLogger(ProductController.class);

	@Autowired
	private IProductService service;

	@ApiOperation(value = "Create a new product", response = ProductVO.class)
	@PostMapping
	public ResponseEntity<ProductVO> createProduct(@RequestBody ProductDTO product) {
		ResponseEntity<ProductVO> response = null;
		logger.info("Method createProduct init");
		ProductVO newProduct= service.createProduct(product);
		response = new ResponseEntity<>(newProduct, HttpStatus.CREATED);
		logger.info("Method createProduct finish");
		return response;
	}

	@ApiOperation(value = "Retrieve a product", response = ProductVO.class)
	@GetMapping("/{sku}")
	public ResponseEntity<ProductVO> getProduct(@PathVariable("sku") Long sku) {
		ResponseEntity<ProductVO> response = null;
		logger.info("Method getProduct init");
		ProductVO product = service.getProductBySku(sku);
		logger.info("Method getProduct finish");
		response = new ResponseEntity<>(product, HttpStatus.OK);
		return response;
	}

	@ApiOperation(value = "Update a product", response = ProductVO.class)
	@PutMapping
	public ResponseEntity<ProductVO> updateProduct(@RequestBody ProductDTO product) {
		ResponseEntity<ProductVO> response = null;
		logger.info("Method updateProduct init");
		ProductVO newProduct= service.updateProduct(product);
		response = new ResponseEntity<>(newProduct, HttpStatus.OK);
		logger.info("Method updateProduct finish");
		return response;
	}

	@ApiOperation(value = "Delete a product")
	@DeleteMapping("/{sku}")
	public ResponseEntity<ProductVO> deleteProduct(@PathVariable("sku") Long sku) {
		logger.info("Method deleteProduct init");
		service.deleteProductBySku(sku);
		logger.info("Method deleteProduct finish");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
