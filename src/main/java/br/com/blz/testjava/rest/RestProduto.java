package br.com.blz.testjava.rest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.model.Warehouses;
import br.com.blz.testjava.repository.ProdutoRepository;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

/**
 * @author Evandro Lopes da Rocha (evandro.esw@gmail.com)
 * @date 13/06/2019.
 */
@RestController
@EnableJpaRepositories(basePackageClasses = ProdutoRepository.class)
@RequestMapping(path="/servicesREST/")
public class RestProduto {
	
	@Autowired
	private ProdutoRepository repository;
	
	RestProduto(ProdutoRepository repository) {
	    this.repository = repository;
	}
	
	@GetMapping(path={"/findAll"})
	public List<Produto> findAll(){
	   return (List<Produto>) repository.findAll();
	}
	
	@GetMapping(path={"/findBySku/{sku}"})
	public Produto findBySku(@PathVariable long sku){
	Produto produto = repository.findBySku(sku);
	long soma = 0;
	if(produto != null){
		for(int i = 0; i < produto.getInventory().getWarehouses().size(); i++){
			soma += produto.getInventory().getWarehouses().get(i).getQuantity();
		}
		produto.getInventory().setQuantity(soma);
		if(produto.getInventory().getQuantity() > 0){
			produto.setMarketable(true);
		}
	}
	   return produto;
	}
	
	
	 @GetMapping(path={"/create"})
		public void create() throws Exception{
		 
		 Produto novoProduto = geraProduto();
		 
		 if(repository.findBySku(novoProduto.getSku()) != null){
			 throw new Exception("######## [ ERRO: JÁ EXISTE UM PRODUTO COM O SKU INFORMADO ] ########");
		 }
		 else{
			 repository.save(novoProduto);
		 }
	 }
		 
	 @GetMapping(path={"/update/{sku}"})
		public void edit(@PathVariable long sku) throws ParseException {
			Produto produto = null;
			Produto updateProduto = null;
			
			if(repository.findBySku(sku) != null){
				produto = geraProduto();
				updateProduto = repository.findBySku(sku);
				if(produto.getSku() == updateProduto.getSku()){
					if(produto.getName() != updateProduto.getName()){	
						updateProduto.setName(produto.getName());
					}
					if(produto.isMarketable() != updateProduto.isMarketable()){	
						updateProduto.setMarketable(produto.isMarketable());
					}
				repository.save(updateProduto);
				}
			}
	 	}
	 
		@GetMapping(path={"/delete/{sku}"})
		public void delete(@PathVariable long sku) {
			Produto produto;
			produto = repository.findBySku(sku);
			if(produto != null){
				repository.delete(produto);
			}
		}
		
		public Produto geraProduto() throws ParseException{
			Produto produto = null;
			Inventory inventory = null;
		    JSONObject jsonObject;
		    JSONObject inventoryObj;
		    List<Warehouses> lista = null;
		    List<JSONObject> jsonLista = new ArrayList<JSONObject>();
		    JSONParser parser = new JSONParser();
		    try {
		        try (FileReader f = new FileReader("C:/DEV/test-java/src/main/resources/input.JSON")) {
		            jsonObject = (JSONObject) parser.parse(f);
		            inventoryObj = (JSONObject) jsonObject.get("inventory");
		            jsonLista = (List<JSONObject>) inventoryObj.get("warehouses");
		        }
			
			       lista = new ArrayList<Warehouses>();
			       Produto sku = (Produto) repository.findBySku((int) jsonObject.get("sku"));
			       if(sku == null){
						for(int i = 0; i < jsonLista.size(); i++){
							Warehouses warehouses = new Warehouses();
							warehouses.setLocality((String) jsonLista.get(i).get("locality"));
							warehouses.setQuantity((int)jsonLista.get(i).get("quantity"));
							warehouses.setType((String)jsonLista.get(i).get("type"));
							repository.saveAndFlush(warehouses);
							lista.add(warehouses);
						}
						
				 		inventory = new Inventory();
						inventory.setQuantity(0);
						inventory.setWarehouses(lista);
				       }
					produto = new Produto();
					produto.setSku((int) jsonObject.get("sku"));
			        produto.setName((String) jsonObject.get("name"));
			        produto.setInventory(inventory);
			        produto.setMarketable((boolean) jsonObject.get("isMarketable"));
		    } catch (FileNotFoundException e) {
		    } catch (IOException e) {
				 }
			return produto;
		}
}
