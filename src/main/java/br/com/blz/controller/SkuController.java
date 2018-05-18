package br.com.blz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.model.Sku;
import br.com.blz.service.AbstractService;
import br.com.blz.service.SkuService;

@RestController
@RequestMapping(value="/api/sku")
public class SkuController extends CrudController<Sku> {
	
	@Autowired
	private SkuService skuService;

	@Override
	AbstractService<Sku> abstractService() {
		return this.skuService;
	}
	
	
}
