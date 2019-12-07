package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.dto.Warehouses;
import br.com.blz.testjava.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository repository;

	public ProductDTO getProduct(int sku) {
		ProductDTO product = repository.getProduct(sku);
		if (product != null) {
			// Toda vez que um produto for recuperado por sku deverá ser calculado a propriedade: inventory.quantity
			calculateInventoryQuantity(product);
			// Toda vez que um produto for recuperado por sku deverá ser calculado a propriedade: isMarketable
			isMarketable(product);
		}
		return product;
	}
	public ProductDTO removeProduct(int sku) {
		return repository.deleteProduct(sku);
	}

	public ProductDTO createProduct(ProductDTO product) throws Exception {
		return repository.createProduct(product);
	}
	
	private void isMarketable(ProductDTO product) {
		product.setIsMarketable(product.getInventory().getQuantity()>0?true:false);
	}
	
	private void calculateInventoryQuantity(ProductDTO product) {
		if (product.getInventory() != null && product.getInventory().getWarehouses() != null) {
			//caso exista algum warehouse com quantidade forçamos o zero para garantir que 
			//um warehouse com falhanao impacte na venda do produto
			product.getInventory().setQuantity(
					product.getInventory()
					.getWarehouses()
					.stream().map((Warehouses w)-> w.getQuantity()>0? w.getQuantity():0)
					.reduce(0, Integer::sum));			
		} else {
			if (product.getInventory() == null) {
				product.setInventory(new InventoryDTO());
			}
			product.getInventory().setQuantity(0);
		}
	}
	public ProductDTO updateProduct(ProductDTO product) {
		return repository.updateProduct(product);
	}

}

