package br.com.blz.testjava.controller;

import static br.com.blz.testjava.config.BelezaNaWebStringConfig.PARAM_SKU;
import static br.com.blz.testjava.config.BelezaNaWebStringConfig.SKU_BASE_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.model.Sku;
import br.com.blz.testjava.service.SkuService;

@RestController
public class SkuController extends BaseController{

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private SkuService skuService;
	
	@RequestMapping(path = SKU_BASE_URL, method = { GET }, produces = {APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ResponseEntity<List<Sku>> getAllSkus() {
		LOG.info("Getting all skus");

		return createResponse(skuService.getAllSkus());
	}

	@RequestMapping(path = SKU_BASE_URL + PARAM_SKU, method = { GET }, produces = {
			APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ResponseEntity<Sku> getSku(@PathVariable("skuId") @NotBlank Long skuId) {
		LOG.info("Getting a sku : {}", skuId);

		return createResponse(skuService.getSku(skuId));
	}

	@RequestMapping(path = SKU_BASE_URL, method = { POST }, produces = { APPLICATION_JSON_UTF8_VALUE }, consumes = {
			APPLICATION_JSON_UTF8_VALUE }) 
	public @ResponseBody ResponseEntity<Sku> createSku(@RequestBody @NotBlank Sku sku) {
		LOG.info("Creating a sku : {}", sku);

		return createResponse(skuService.createSku(sku));
	}

	@RequestMapping(path = SKU_BASE_URL, method = { PUT }, produces = { APPLICATION_JSON_UTF8_VALUE }, consumes = {
			APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ResponseEntity<Sku> updateProduct(@RequestBody @NotBlank Sku sku) {
		LOG.info("Updating a sku : {}", sku);
		return createResponse(skuService.updateSku(sku));
	}
	

	@RequestMapping(path = SKU_BASE_URL + PARAM_SKU, method = { DELETE }, produces = {APPLICATION_JSON_UTF8_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public void deleteSku(@PathVariable("skuId") @NotBlank Long skuId) {
		LOG.info("Deleting a product : {}", skuId);
		skuService.deleteSku(skuId);
	}
}
