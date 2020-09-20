package br.com.blz.testjava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.model.SKU;
import br.com.blz.testjava.service.SKUService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "TestJavaBlz", protocols = "https")
@RestController
@RequestMapping(path = "/testjava")
@CrossOrigin(origins = "*")
public class SKUController {

	@Autowired
	private SKUService skuService;

	public SKUController() {
		super();
	}
	
	public SKUController(SKUService skuService) {
		super();
		this.skuService = skuService;
	}

	
	@ApiOperation(value = "Obter todos os skus")
	@GetMapping(produces = "application/json", path = "/skus")
	public List<SKU> getAll() throws Exception {
		return skuService.getAll();
	}
	
	@ApiOperation(value = "Obter sku")
	@GetMapping(produces = "application/json", path = "/sku/{sku}")
	public SKU get(@RequestParam Integer key) throws Exception {
		return skuService.get(key);
	}
	
	@ApiOperation(value = "Inserir sku")
	@PostMapping(consumes = "application/json", produces = "application/json", path = "/sku")
	public SKU insert(@RequestBody SKU param) throws Exception {
		return skuService.insert(param);
	}
	
	@ApiOperation(value = "Editar sku")
	@PutMapping(consumes = "application/json", produces = "application/json", path = "/sku/{sku}")
	public SKU update(@RequestParam Integer key, @RequestBody SKU param) throws Exception {
		return skuService.update(key, param);
	}
	
	@ApiOperation(value = "Deletar por sku")
	@DeleteMapping(produces = "application/json", path = "/sku/{sku}")
	public SKU delete(@RequestParam Integer key) throws Exception {
		return skuService.delete(key);
	}
}