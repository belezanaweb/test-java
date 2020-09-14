package br.com.blz.testjava.product;

import br.com.blz.testjava.exception.NotFoundProductException;
import br.com.blz.testjava.exception.ProductSkuExistsException;
import br.com.blz.testjava.inventory.InventoryEntity;
import br.com.blz.testjava.inventory.InventoryRepository;
import br.com.blz.testjava.utils.Constants;
import br.com.blz.testjava.warehouse.WarehouseEntity;
import br.com.blz.testjava.warehouse.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    public ProductEntity findBySku(String sku) throws NotFoundProductException {
        Optional<ProductEntity> product = productRepository.findBySku(sku);
        if(!product.isPresent()){
            throw new NotFoundProductException(Constants.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        return buildProduct(product.get());
    }


    public void save(ProductEntity productEntity) throws ProductSkuExistsException {
        String skuProduct = productEntity.getSku();
        if(productRepository.findBySku(skuProduct).isPresent()){
            throw new ProductSkuExistsException(Constants.PRODUCT_SKU_EXISTS_EXCEPTION_MESSAGE);
        } else {
            saveProductTransaction(productEntity);
        }
    }

    public void update(ProductEntity productEntity, String sku) throws NotFoundProductException {
        if(!productRepository.findBySku(sku).isPresent()){
            throw new NotFoundProductException(Constants.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE);
        }
//        productRepository.updateProduct(productEntity, sku);
    }

    public ProductEntity buildProduct(ProductEntity productEntity){
        InventoryEntity inventoryEntity = productEntity.getInventory();
        List<WarehouseEntity> warehouseEntities = inventoryEntity.getWarehouses();
        inventoryEntity.setQuantity(warehouseEntities.stream().mapToInt(o -> o.getQuantity()).sum());
        if(inventoryEntity.getQuantity() > 0){
            productEntity.setMarketable(true);
        }
        return productEntity;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void delete(String sku) {
        productRepository.deleteBySku(sku);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void saveProductTransaction(ProductEntity productEntity){
        InventoryEntity inventoryEntity = productEntity.getInventory();
        if(Objects.nonNull(inventoryEntity) && Objects.nonNull(inventoryEntity.getWarehouses())){
            productEntity.getInventory().getWarehouses().stream().forEach(warehouseEntity -> {
                warehouseRepository.saveAndFlush(warehouseEntity);
            });
            inventoryRepository.saveAndFlush(productEntity.getInventory());
        }
        productRepository.save(productEntity);
    }

}
