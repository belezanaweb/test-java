package br.com.blz.testjava.business;

import br.com.blz.testjava.entity.InventoryEntity;
import br.com.blz.testjava.entity.ProductEntity;
import br.com.blz.testjava.entity.WarehouseEntity;
import br.com.blz.testjava.exception.ProductAlreadyExistException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import br.com.blz.testjava.repository.WarehouseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductBusiness {

    @Autowired
    private ProductRepository produtoRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public Product save(Product product) throws ProductAlreadyExistException {
        ProductEntity entity = produtoRepository.findBySku(product.getSku());
        if(entity != null){
            throw new ProductAlreadyExistException("Produto com Sku já cadastrado");
        }

        ProductEntity productEntity = objectMapper.convertValue(product, ProductEntity.class);
        produtoRepository.save(productEntity);
        return objectMapper.convertValue(entity, Product.class);
    }

    public Product getBySku(Long sku){
        ProductEntity entity = produtoRepository.findBySku(sku);
        return Optional.ofNullable(entity).map(product -> {
            List<WarehouseEntity> list = warehouseRepository.findByInventory(product.getInventory().getId());
            product.getInventory().setWarehouses(list);
            product = updateQuantity(product);
            return objectMapper.convertValue(product, Product.class);
        }).orElse(null);
    }

    public void delete(Long sku){
        produtoRepository.deleteProductBySku(sku);
    }

    public Product update(Long sku, Product product){
        ProductEntity entity = produtoRepository.findBySku(sku);
        return Optional.ofNullable(entity).map(prod -> {
            prod = updateQuantity(prod);
            prod.setName(product.getName());
            InventoryEntity inventory = objectMapper.convertValue(product.getInventory(), InventoryEntity.class);
            prod.setInventory(inventory);
            ProductEntity productEntity = produtoRepository.save(prod);
            return objectMapper.convertValue(productEntity, Product.class);
        }).orElse(product);
    }

    private ProductEntity updateQuantity(ProductEntity productEntity){
        int quantityWarehouse = productEntity.getInventory().getWarehouses().stream().mapToInt(w -> w.getQuantity()).sum();
        productEntity.getInventory().setQuantity(quantityWarehouse);
        boolean isMarketable = productEntity.getInventory().getQuantity() > 0 ? true : false;
        productEntity.setMarketable(isMarketable);
        return productEntity;
    }
}
