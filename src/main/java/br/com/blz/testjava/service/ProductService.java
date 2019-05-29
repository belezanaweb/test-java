package br.com.blz.testjava.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.exception.BusinessException;

@Service
public class ProductService {
	
	private static Map<Long, ProductDTO> listProducts;
	
	public ProductService() {
		if(listProducts==null) {
			listProducts = new HashMap<Long, ProductDTO>();
		}
	}
	
	public void insert(ProductDTO productDTO) throws BusinessException {
		if(listProducts.containsKey(productDTO.getSku())) {
			throw BusinessException.getInstance("Produto já existente com o sku informado");
		}
		listProducts.put(productDTO.getSku(), productDTO);
	}
	
	public ProductDTO update(Long sku, ProductDTO productDTO) throws BusinessException {
		if(!listProducts.containsKey(sku)) {
			throw BusinessException.getInstance("Produto não existente com o sku informado");
		}
		productDTO.setSku(sku);
		listProducts.put(sku, productDTO);
		return productDTO;
		
	}
	
	public void delete(Long sku) {
		listProducts.remove(sku);
	}
	
	public ProductDTO find(Long sku) throws BusinessException {
		if(!listProducts.containsKey(sku)) {
			throw BusinessException.getInstance("Produto não existente com o sku informado");
		}
		listProducts.get(sku).setIsMarketable(listProducts.get(sku).getInventory().getQuantity()>0?true:false);
		return listProducts.get(sku);
	}

}
