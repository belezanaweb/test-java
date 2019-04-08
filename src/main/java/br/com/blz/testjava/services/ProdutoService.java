package br.com.blz.testjava.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.blz.testjava.dto.ProdutoDTO;
import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Produto;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.exception.SkuException;
import br.com.blz.testjava.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto createProduto(ProdutoDTO dto) throws SkuException {

		Produto produto = new Produto();
		Inventory inventory = new Inventory();
		
		List<Warehouse> warehouses = new ArrayList<>();
		
		produto.setName(dto.getName());

		if (dto.getSku() != null) {
			
			Produto prod = produtoRepository.findBySku(dto.getSku());

			if (prod != null) {
				if (dto.getSku().equalsIgnoreCase(prod.getSku())) {
					throw new SkuException("SKU existente!");
				}
			}
			
		}

		produto.setSku(dto.getSku().toUpperCase());

		if (!CollectionUtils.isEmpty(dto.getInventory().getWarehouses())) {

			long count = dto.getInventory().getWarehouses().stream().count();

			dto.getInventory().getWarehouses().stream().forEach(ware -> {
				Warehouse warehouse = new Warehouse();
				warehouse.setLocality(ware.getLocality());
				warehouse.setQuantity(ware.getQuantity());
				warehouse.setType(ware.getType());

				int quantity = 0;

				for (int i = 0; i < count; i++) {
					
					quantity = quantity + ware.getQuantity();
					
				}

				dto.getInventory().setQuantity(quantity);
				warehouses.add(warehouse);
			});
			
			inventory.setQuantity(dto.getInventory().getQuantity());
			
			inventory.setWarehouse(warehouses);
			produto.setInventory(inventory);
			
			if (produto.getInventory().getQuantity() > 0) {
				produto.setIsMarketable(true);
			} else {
				produto.setIsMarketable(false);
			}

		}

		produtoRepository.save(produto);

		return produto;
	}
	
	public Produto buscarProduto(String sku) {
		
		Produto produto = produtoRepository.findBySku(sku);
		
		if (produto != null) {
			
			return produto;
			
		} 

		return null;
	}

	public Produto atualizarProduto(ProdutoDTO dto, String sku) throws SkuException {
		
		Produto prod = new Produto();
		
		Inventory inventory = new Inventory();
		List<Warehouse> warehouses = new ArrayList<>();

		Produto produto = produtoRepository.findBySku(sku);
		
		produto.setName(dto.getName());
		produto.setSku(dto.getSku().toUpperCase());

		if (!CollectionUtils.isEmpty(dto.getInventory().getWarehouses())) {

			long count = dto.getInventory().getWarehouses().stream().count();

			dto.getInventory().getWarehouses().stream().forEach(ware -> {
				Warehouse warehouse = new Warehouse();
				warehouse.setLocality(ware.getLocality());
				warehouse.setQuantity(ware.getQuantity());
				warehouse.setType(ware.getType());

				int quantity = 0;

				for (int i = 0; i < count; i++) {
					
					quantity = quantity + ware.getQuantity();
					
				}

				dto.getInventory().setQuantity(quantity);
				warehouses.add(warehouse);
				
			});
			
			inventory.setQuantity(dto.getInventory().getQuantity());
			
			inventory.setWarehouse(warehouses);
			prod.setInventory(inventory);
			
			if (prod.getInventory().getQuantity() > 0) {
				prod.setIsMarketable(true);
			} else {
				prod.setIsMarketable(false);
			}

		}

		produtoRepository.saveAndFlush(produto);

		return produto;
	}
	
	public void deleteProduto(String sku) throws SkuException {
		
		Produto produto = produtoRepository.findBySku(sku);
		if (produto != null) {
			produtoRepository.delete(produto);
		}
		
	}
}
