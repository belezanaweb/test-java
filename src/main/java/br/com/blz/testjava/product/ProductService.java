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

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductEntity findBySku(Long sku) throws NotFoundProductException {
        ProductEntity product = productRepository.findBySku(sku);
        if(Objects.isNull(product)){
            throw new NotFoundProductException(Constants.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        return buildProduct(product);
    }


    public ProductEntity save(ProductEntity productEntity) throws ProductSkuExistsException {
        Long skuProduct = productEntity.getSku();
        if(Objects.nonNull(productRepository.findBySku(skuProduct))){
            throw new ProductSkuExistsException(Constants.PRODUCT_SKU_EXISTS_EXCEPTION_MESSAGE);
        } else {
            return saveProductTransaction(productEntity);
        }
    }

    public void update(ProductEntity productEntity, Long sku) throws NotFoundProductException {
        if(Objects.isNull(productRepository.findBySku(sku))){
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
    public ProductEntity saveProductTransaction(ProductEntity productEntity){
        return buildProduct(productRepository.save(productEntity));
    }

}
