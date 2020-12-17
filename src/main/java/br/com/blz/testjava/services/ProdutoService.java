package br.com.blz.testjava.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.blz.testjava.models.Inventory;
import br.com.blz.testjava.models.Produto;
import br.com.blz.testjava.models.WareHouse;

@Component
public class ProdutoService {


	private HashMap<Long,Produto> produtos = new HashMap<Long,Produto>();
	
	public ProdutoService(){init();}
	
	private void init() {
		WareHouse a1 = new WareHouse("SP",12,"ECOMMERCE");
		WareHouse a2 = new WareHouse("MOEMA",3,"PHYSICAL_STORE");
		Inventory inv = new Inventory();
		inv.addWarehouse(a1);
		inv.addWarehouse(a2);
		
		Produto p = new Produto();
		p.setSku(1);
		p.setInventory(inv);
		p.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
		
		produtos.put(p.getSku(),p);
	}
	
	public List<Produto> findAll(){
		
		return new ArrayList<Produto>(produtos.values());
	}

	public Produto findById(long id) {
		return produtos.get(id);
	}

	public Produto save(Produto produto) {
		produtos.put(produto.getSku(), produto);
		return produto;
	}

	public void delete(Produto produto) {
		produtos.remove(produto.getSku());
	}
}
