package br.com.blz.testjava.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.dominio.Sku;
import br.com.blz.testjava.dto.SkuDTO;
import br.com.blz.testjava.services.SkuService;

@RestController
@RequestMapping(value = "/skus")
public class SkuResource {

	@Autowired
	private SkuService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SkuDTO>> findAll() {

		List<Sku> list = service.findAll();
		List<SkuDTO> listDTO = list.stream().map(obj -> new SkuDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/{sku}",method=RequestMethod.GET)
	public ResponseEntity<SkuDTO> find(@PathVariable Integer sku) {
		
		Sku obj = service.findBySku(sku);
		SkuDTO skuDTO = new SkuDTO(obj);
		return ResponseEntity.ok().body(skuDTO);
	}
	
	
}
