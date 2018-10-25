package br.com.blz.testjava.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dao.ProductDAO;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Warehouse;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;

	public Product findProductBySku(Integer sku) {
		
		Product product = this.productDAO.find(sku);
		
		if (product == null) {
			throw new RuntimeException("ERRO: O Produto NÃO existe na base de dados da Beleza na Web!");
		}
		
		Integer somaQuantity = product.getInventory().getWarehouses().stream().map(Warehouse::getQuantity).reduce(Integer::sum).get();
		
		product.getInventory().setQuantity(somaQuantity);
		
		if (somaQuantity > 0) {
			product.setMarketable(true);
		}
		
		return product; 
	}

	public Product saveProduct(ProductDTO productDTO) {
		
		try {
			Product product = new Product();
			
			product.setSku(productDTO.getSku());
			product.setName(productDTO.getName());
			product.getInventory().setWarehouses(productDTO.getInventory().getWarehouses());
			
			return this.productDAO.save(product);
		} catch (DataIntegrityViolationException e) {
			throw new RuntimeException("ERRO: Não foi possível inserir o Produto, pois ele já existe na base de dados da Beleza na Web!");
		}
		
	}

	public Product updateProduct(Integer sku, ProductDTO productDTO) {
		
		Product productFound = this.productDAO.find(sku);
		
		if (productFound != null) {
			productFound.setName(productDTO.getName());
			
			List<Warehouse> warehousesFound = productFound.getInventory().getWarehouses();
			List<Warehouse> warehousesDTO = productDTO.getInventory().getWarehouses();
			List<Warehouse> warehouses = new ArrayList<>();
			
			boolean naoExiste = true;
			
			for (Warehouse warehouseDTO : warehousesDTO) {
				
				for (Warehouse warehouseFound : warehousesFound) {
					
					if (warehouseDTO.equals(warehouseFound)) {
						warehouseFound.setQuantity(warehouseDTO.getQuantity());
						warehouses.add(warehouseFound);
						naoExiste = false;
						break;
					}
					
				}
				
				if (naoExiste) {
					warehouses.add(warehouseDTO);
				} else {
					naoExiste = true;
				}
				
			}
			
			productFound.getInventory().setWarehouses(warehouses);
			
			return this.productDAO.update(productFound);
		} else {
			throw new RuntimeException("ERRO: Não foi possível atualizar o Produto, pois ele NÃO existe na base de dados da Beleza na Web!");
		}
		
	}
	
	public boolean deleteProduct(Integer sku) {
		
		Product productFound = this.productDAO.find(sku);
		
		if (productFound != null) {
			this.productDAO.delete(productFound);
			return true;
		} else {
			return false;
		}
		
	}
	
}
