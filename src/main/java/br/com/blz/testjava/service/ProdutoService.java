package br.com.blz.testjava.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.model.ProdutoRepository;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.model.WarehouseId;
import br.com.blz.testjava.model.WarehouseType;
import br.com.blz.testjava.payload.InventoryPayload;
import br.com.blz.testjava.payload.ProdutoPayload;
import br.com.blz.testjava.payload.WarehousePayload;

@Service
public class ProdutoService {

	
	@Autowired
	private ProdutoRepository produtoRepository;


	/** 
	 * Verifica antes se não existia.
	 */
	public void incluir(ProdutoPayload produtoPayload) throws ProdutoException {
		
		Optional<Produto> optionalProduto = produtoRepository.findById(produtoPayload.getSku());
		if(optionalProduto.isPresent()) {
			throw new ProdutoException("ERRO: Produto já cadastrado");
		}
		else {	
			Produto produto = payloadToEntity(produtoPayload) ;
			produtoRepository.save(produto);
		}
	}

	
	@Transactional
	public void atualizar(ProdutoPayload produtoPayload) throws ProdutoException {
		Optional<Produto> optionalProduto = produtoRepository.findById(produtoPayload.getSku());
		if(!optionalProduto.isPresent()) {
			throw new ProdutoException("ERRO: Produto não cadastrado");
		}
		else {
			//apaga com cascade p/ os warehouses
			produtoRepository.delete(optionalProduto.get());

			Produto produto = payloadToEntity(produtoPayload) ;
			produtoRepository.save(produto);
		}

	}	

	
	public ProdutoPayload consultar(Integer sku) throws ProdutoException {
		
		Optional<Produto> optionalProduto = produtoRepository.findById(sku);
		if(!optionalProduto.isPresent()) {
			throw new ProdutoException("ERRO: Produto não cadastrado");
		}
		else {
			return entityToPayload(optionalProduto.get());
		}
	}

	
	public void apagar(Integer sku) throws ProdutoException {		
		//Long qtd = produtoRepository.deleteById(sku);
		Long qtd = produtoRepository.deleteBySku(sku);
		
		if(qtd == 0) {
			throw new ProdutoException("ERRO: Produto não cadastrado");			
		}
		
	}	

	
	/** Converte um ProdutoPayload para Produto 
	 * @throws ProdutoException 
	 *   Valida os atributos obrigatórios.
	 * 
	 */
	public Produto payloadToEntity(ProdutoPayload produtoPayload) throws ProdutoException {
		Produto produto = new Produto();
		
		if (produtoPayload.getSku() == null || produtoPayload.getSku() < 1) {
			throw new ProdutoException("ERRO: sku inválido");
		}
				
		produto.setSku(produtoPayload.getSku());
		produto.setName(produtoPayload.getName());		
		
		if(produtoPayload.getInventory() != null && 
				produtoPayload.getInventory().getWarehouses() != null &&
				produtoPayload.getInventory().getWarehouses().size() > 0) {

			produto.setListWarehouses(new ArrayList<>());
			
			for(WarehousePayload warehousePayload : produtoPayload.getInventory().getWarehouses()) {
				Warehouse warehouse = new Warehouse();
				warehouse.setId(new WarehouseId());
				warehouse.getId().setSku(produto.getSku());
				
				if(warehousePayload.getLocality() == null) {
					throw new ProdutoException("ERRO: Warehouse sem localidade");
				}
				else {
					warehouse.getId().setLocality(warehousePayload.getLocality());
				}
				
				
				//se não tiver quantidade, nem inclui este warehouse
				if(warehousePayload.getQuantity() == null || warehousePayload.getQuantity() == 0) {
					continue;
				}
				else {
					warehouse.setQuantity(warehousePayload.getQuantity());
					
					//tipos válidos de acordo com o Enum.
					WarehouseType warehouseType = WarehouseType.getWarehouseType(  warehousePayload.getType() );
					if(warehouseType == null) {
						throw new ProdutoException("ERRO: Tipo de warehouse inválido");
					}
					else {
						warehouse.setType(warehouseType);
					}
					
					//verifica duplicidade: mesmo sku e localidade
					if(produto.getListWarehouses().contains(warehouse)) {
						throw new ProdutoException("ERRO: warehouse duplicado");
					}
					else {
						produto.getListWarehouses().add(warehouse);
					}
					
				}
			}

			
		}
		
		return produto;
	}
	
	
	/** Converte um Produto para ProdutoPayload 
	 * @throws ProdutoException */
	public ProdutoPayload entityToPayload(Produto produto) throws ProdutoException {
		ProdutoPayload produtoPayload = new ProdutoPayload();
		
		produtoPayload.setInventory(new InventoryPayload());
		
		if(produto.getListWarehouses() != null ) {
			
			produtoPayload.getInventory().setWarehouses(new ArrayList<>());
			
			for(Warehouse warehouse: produto.getListWarehouses()) {
				WarehousePayload warehousePayload = new WarehousePayload();

				warehousePayload.setLocality(warehouse.getId().getLocality());
				warehousePayload.setQuantity(warehouse.getQuantity());
				warehousePayload.setType(warehouse.getType().name());
				produtoPayload.getInventory().setQuantity(produtoPayload.getInventory().getQuantity() +  
						warehouse.getQuantity());
								
				produtoPayload.getInventory().getWarehouses().add(warehousePayload);
			}
		}
		
		produtoPayload.setSku(produto.getSku());
		produtoPayload.setName(produto.getName());
		
		return produtoPayload;
	}
	

	
}
