package br.com.blz.testjava.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.dto.WarehouseDTO;
import br.com.blz.testjava.exception.ProductAlreadyExistException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.InventoryModel;
import br.com.blz.testjava.model.ProductModel;
import br.com.blz.testjava.model.WarehouseModel;
import br.com.blz.testjava.repository.ProductRepositoryImpl;

@Service
public class ProductService {

    private ProductRepositoryImpl repository;

    public ProductService( ProductRepositoryImpl repository){
        this.repository = repository;
    }
    
    public void updateProduct(final ProductDTO product) {
    	repository.updateProduct(convertProductDTOToProductModel(product));
    }

    public void createProduct(final ProductDTO product) {
    	ProductModel productRepo = repository.findProductBySku(product.getSku());
    	if(productRepo != null) {
    		throw new ProductAlreadyExistException("Produto já cadastrado. Sku: "+ product.getSku());
    	}
        repository.createProduct(convertProductDTOToProductModel(product));
    }
    
    public void deleteProduct(final Integer sku) {
    	repository.deleteProduct(sku);
    }

    public ProductDTO retrieveProduct(Integer sku) {
    	ProductModel productModel = repository.findProductBySku(sku);
    	if(productModel == null) {
    		throw new ProductNotFoundException("Produto com sku: " + sku + " não encontrado");
    	}
    	return convertProductModelToProductDTO(productModel);
    }

    public ProductModel convertProductDTOToProductModel(ProductDTO productDTO) {
    	ProductModel productModel = new ProductModel();
    	productModel.setSku(productDTO.getSku());
    	productModel.setName(productDTO.getName());
    	
    	productModel.setInventory(new InventoryModel());
    	productModel.getInventory().setWarehouses(productDTO.getInventory().getWarehouses().stream()
    	    	.map(it-> {
    	    		WarehouseModel warehouseModel = new WarehouseModel();
    	    		warehouseModel.setLocality(it.getLocality());
    	    		warehouseModel.setQuantity(it.getQuantity());
    	    		warehouseModel.setType(it.getType());
    	    		return warehouseModel;
    	    		
    	    	}).collect(Collectors.toList()));
    	return productModel;    	
    }
    
    public ProductDTO convertProductModelToProductDTO(ProductModel productModel) {
    	ProductDTO productDTO = new ProductDTO();
    	productDTO.setSku(productModel.getSku());
    	productDTO.setName(productModel.getName());
    	
    	productDTO.setInventory(new InventoryDTO());
    	productDTO.getInventory().setWarehouses(productModel.getInventory().getWarehouses().stream()
    	    	.map(it-> {
    	    		WarehouseDTO warehouseDTO = new WarehouseDTO();
    	    		warehouseDTO.setLocality(it.getLocality());
    	    		warehouseDTO.setQuantity(it.getQuantity());
    	    		warehouseDTO.setType(it.getType());
    	    		return warehouseDTO;
    	    		
    	    	}).collect(Collectors.toList()));
    	productDTO.getInventory().setQuantity(productModel.getInventory().getWarehouses().stream()
    			.filter(it-> it.getQuantity()!=null)
                .mapToInt(it -> it.getQuantity())
                .sum());
    	productDTO.setIsMarketable(productDTO.getInventory().getQuantity() > 0);
    	return productDTO;
		
	}
}
