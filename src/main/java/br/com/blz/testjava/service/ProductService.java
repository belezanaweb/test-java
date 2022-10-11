package br.com.blz.testjava.service;

import br.com.blz.testjava.dto.InventoryDTO;
import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.dto.WarehouseDTO;
import br.com.blz.testjava.exception.ProductAlreadyExistException;
import br.com.blz.testjava.exception.ProductNotFoundException;
import br.com.blz.testjava.model.InventoryEntity;
import br.com.blz.testjava.model.ProductEntity;
import br.com.blz.testjava.model.WarehouseEntity;
import br.com.blz.testjava.repository.ProductRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepositoryImpl repository;

    public ProductService(ProductRepositoryImpl repository){
        this.repository = repository;
    }

    public void createProduct(final ProductDTO product) {
        ProductEntity productEntity = repository.findProductBySku(product.getSku());
        if(productEntity != null) {
            throw new ProductAlreadyExistException("Product " + product.getSku() + " already registered.");
        }
        repository.createProduct(product.buildProductEntity(product));
    }
//    public void createProduct(final ProductDTO product) {
//        ProductEntity productEntity = repository.findProductBySku(product.getSku());
//        if(productEntity != null) {
//            throw new ProductAlreadyExistException("Product " + product.getSku() + " already registered.");
//        }
//        repository.createProduct(buildProductEntity(product));
//    }

    public void updateProduct(final ProductDTO product,Integer sku) {
        ProductEntity productEntity = repository.findProductBySku(sku);
        if(productEntity == null) {
            throw new ProductNotFoundException("Product " + sku + " not found.");
        }
        repository.updateProduct(product.buildProductEntity(product));
    }

//    public void updateProduct(final ProductDTO product,Integer sku) {
//        ProductEntity productEntity = repository.findProductBySku(sku);
//        if(productEntity == null) {
//            throw new ProductNotFoundException("Product " + sku + " not found.");
//        }
//        repository.updateProduct(buildProductEntity(product));
//    }

    public ProductDTO recoveryProduct(Integer sku) {
        ProductEntity productEntity = repository.findProductBySku(sku);
        if(productEntity == null) {
            throw new ProductNotFoundException("Product " + sku + " not found.");
        }
        return productEntity.buildProductDTO(productEntity);
    }

    public void deleteProduct(final Integer sku) {
        ProductEntity productEntity = repository.findProductBySku(sku);
        if(productEntity == null) {
            throw new ProductNotFoundException("Product " + sku + " not found.");
        }
        repository.deleteProduct(sku);
    }

//    public ProductEntity buildProductEntity(ProductDTO productDTO) {
//        ProductEntity productEntity = new ProductEntity();
//        productEntity.setSku(productDTO.getSku());
//        productEntity.setName(productDTO.getName());
//        productEntity.setInventory(new InventoryEntity());
//        productEntity.getInventory().setWarehouses(productDTO.getInventory().getWarehouses().stream()
//            .map(it-> {
//                WarehouseEntity warehouseEntity = new WarehouseEntity();
//                warehouseEntity.setLocality(it.getLocality());
//                warehouseEntity.setQuantity(it.getQuantity());
//                warehouseEntity.setType(it.getType());
//                return warehouseEntity;
//
//            }).collect(Collectors.toList()));
//        return productEntity;
//    }

//    public ProductDTO buildProductDTO(ProductEntity productEntity) {
//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setSku(productEntity.getSku());
//        productDTO.setName(productEntity.getName());
//        productDTO.setInventory(new InventoryDTO());
//        productDTO.getInventory().setWarehouses(productEntity.getInventory().getWarehouses().stream()
//            .map(it-> {
//                WarehouseDTO warehouseDTO = new WarehouseDTO();
//                warehouseDTO.setLocality(it.getLocality());
//                warehouseDTO.setQuantity(it.getQuantity());
//                warehouseDTO.setType(it.getType());
//                return warehouseDTO;
//
//            }).collect(Collectors.toList()));
//        productDTO.getInventory().setQuantity(productEntity.getInventory().getWarehouses().stream()
//            .filter(it-> it.getQuantity()!=null)
//            .mapToInt(WarehouseEntity::getQuantity)
//            .sum());
//        productDTO.setIsMarketable(productDTO.getInventory().getQuantity() > 0);
//        return productDTO;
//    }

}
