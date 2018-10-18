package br.com.blz.testjava.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.blz.testjava.dominio.Product;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProductDTO>> findAll() {

		List<Product> list = service.findAll();
		List<ProductDTO> listDTO = list.stream().map(obj -> new ProductDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/{sku}",method=RequestMethod.GET)
	public ResponseEntity<ProductDTO> find(@PathVariable Long sku) {
		
		Product obj = service.findBySku(sku);
		ProductDTO skuDTO = new ProductDTO(obj);
		
		return ResponseEntity.ok().body(skuDTO);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Product obj){
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{i}").buildAndExpand(obj.getSku()).toUri();
		return ResponseEntity.created(uri).build();		
	}
	
	@RequestMapping(value= "/{sku}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Product obj, @PathVariable Long sku){
		obj.setSku(sku); //Apenas para garantir o sku no objeto.
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value= "/{sku}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long sku){		
		service.delete(sku);		
		return ResponseEntity.noContent().build();
	}
	
}
