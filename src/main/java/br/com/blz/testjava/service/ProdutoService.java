package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.blz.testjava.dto.ProdutoDTO;
import br.com.blz.testjava.entity.Produto;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.exception.SkuException;
import br.com.blz.testjava.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public Produto createProduto(ProdutoDTO dto) throws SkuException {

		Produto produto = new Produto();
		Warehouse warehouse = new Warehouse();

		produto.setName(dto.getName());

		if (dto.getSku() != null) {

			Produto prod = findBySku(dto.getSku());

			if (dto.getSku().equalsIgnoreCase(prod.getSku())) {

				throw new SkuException("SKU existente!");
			}
		}

		produto.setSku(dto.getSku().toUpperCase());

		if (!CollectionUtils.isEmpty(dto.getInventory().getWarehouses())) {

			long count = dto.getInventory().getWarehouses().stream().count();

			dto.getInventory().getWarehouses().stream().forEach(ware -> {
				warehouse.setLocality(ware.getLocality());
				warehouse.setQuantity(ware.getQuantity());
				warehouse.setType(ware.getType());

				Integer quantity = null;

				for (int i = 0; i <= count; i++) {
					quantity = quantity + ware.getQuantity();
				}

				produto.getInventory().setQuantity(quantity);

			});

			if (produto.getInventory().getQuantity() > 0) {
				produto.setIsMarketable(true);
			} else {
				produto.setIsMarketable(false);
			}

		}

		produto.getInventory().getWarehouses().add(warehouse);

		repository.save(produto);

		return produto;
	}

	public Produto findBySku(String sku) {

		Produto produto = repository.findBySku(sku);

//		if (!CollectionUtils.isEmpty(produto.getInventory().getWarehouses())) {
//
//			long count = produto.getInventory().getWarehouses().stream().count();
//
//			produto.getInventory().getWarehouses().stream().forEach(warehouse -> {
//
//				Integer quantity = null;
//
//				for (int i = 0; i <= count; i++) {
//					quantity = quantity + warehouse.getQuantity();
//				}
//
//				produto.getInventory().setQuantity(quantity);
//
//			});
//
//			if (produto.getInventory().getQuantity() > 0) {
//				produto.setIsMarketable(true);
//			} else {
//				produto.setIsMarketable(false);
//			}
//		}

		return produto;
	}

	public Produto atualizarProduto(ProdutoDTO dto, String sku) {

		Warehouse warehouse = new Warehouse();
		Produto produto = findBySku(sku);

		produto.setName(dto.getName());
		produto.setSku(dto.getSku().toUpperCase());

		if (!CollectionUtils.isEmpty(dto.getInventory().getWarehouses())) {

			long count = dto.getInventory().getWarehouses().stream().count();

			dto.getInventory().getWarehouses().stream().forEach(ware -> {
				
				warehouse.setLocality(ware.getLocality());
				warehouse.setQuantity(ware.getQuantity());
				warehouse.setType(ware.getType());

				Integer quantity = null;

				for (int i = 0; i <= count; i++) {
					quantity = quantity + ware.getQuantity();
				}

				produto.getInventory().setQuantity(quantity);

			});

			if (produto.getInventory().getQuantity() > 0) {
				produto.setIsMarketable(true);
			} else {
				produto.setIsMarketable(false);
			}
		}

		produto.getInventory().getWarehouses().add(warehouse);
		
		repository.save(produto);

		return produto;
	}
	
	public void deleteProduto(String sku) {
		repository.deleteBySku(sku);
	}

}
