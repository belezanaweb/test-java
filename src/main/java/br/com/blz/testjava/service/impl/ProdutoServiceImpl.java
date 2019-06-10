package br.com.blz.testjava.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService{

	public static List<Produto> produtos = new ArrayList<Produto>();
	
	public Produto getProduto(Long sku) throws Exception {		
		Produto produtoRet = new Produto();		
		for(Produto produto : produtos) {			
			if(produto.getSku().equals(sku)) {	
				produtoRet = produto;
				produtoRet = atualizaInventory(produtoRet);
				break;
			}			
		}
		
		if(produtoRet.getSku() == null) {
			throw new Exception("Produto informado não existe.");
		}
		
		return produtoRet;		
	}
	
	public void updateProduto(Produto produto) throws Exception {
		
		boolean prodExiste = false;
		
		for(int i=0; i<produtos.size(); i++) {
			
			Produto produto2 = produtos.get(i);
			
			if(produto2.getSku().equals(produto.getSku())) {				
				produto = atualizaInventory(produto);				
				produtos.set(i, produto);	
				prodExiste = true;
				break;
			}
						
		}
		
		if(!prodExiste) {
			throw new Exception("Produto informado não existe");
		}
		
	}
				
	public void addProduto(Produto produto) throws Exception{
				
		for(Produto produto2 : produtos) {			
			if(produto2.getSku().equals(produto.getSku())) {
				throw new Exception("Produto informado já existe.");
			}			
		}
		
		produto = atualizaInventory(produto);		
		produtos.add(produto);
		
	}
		
	public void delProduto(Long sku) throws Exception{
		
		boolean prodExiste = false;
		
		for(Produto produto : produtos) {			
			if(produto.getSku().equals(sku)) {				
				produtos.remove(produto);
				prodExiste = true;
				break;
			}			
		}				
		
		if(!prodExiste) {
			throw new Exception("Produto informado não existe");
		}
		
	}
	
	private Produto atualizaInventory(Produto produto) {
		
		Long qtdInventory = new Long(0);
		
		if(produto.getInventory() != null) {
		
			for(Warehouse warehouse : produto.getInventory().getWarehouses() ) {				
				qtdInventory += warehouse.getQuantity();				
			}
			
			produto.getInventory().setQuantity(qtdInventory);
			
		}
		
		if(qtdInventory>0) {
			produto.setMarketable(true);
		}else {
			produto.setMarketable(false);
		}
		
		return produto;
	}
	
	
}
