package br.com.blz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.model.Sku;
import br.com.blz.service.AbstractService;
import br.com.blz.service.SkuService;
import br.com.blz.validator.SkuValidator;

@RestController
@RequestMapping(value="/api/sku")
public class SkuController extends CrudController<Sku> {
	
	@Autowired
	private SkuService skuService;
	
	@Autowired
	@Qualifier("skuValidator")
	private SkuValidator skuValidator;
	
	@InitBinder("sku")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(skuValidator);
	}	

	@Override
	AbstractService<Sku> abstractService() {
		return this.skuService;
	}
	
	
}
