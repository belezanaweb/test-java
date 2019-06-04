package br.com.blz.testjava.business;

import br.com.blz.testjava.entity.ProductEntity;
import br.com.blz.testjava.exception.ProductAlreadyExistException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductBusiness {

    @Autowired
    private ProductRepository produtoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public Product save(Product product) throws ProductAlreadyExistException {
        ProductEntity entity = produtoRepository.findBySku(product.getSku());
        if(entity != null){
            throw new ProductAlreadyExistException("Produto com Sku já cadastrado");
        }

        ProductEntity productEntity = objectMapper.convertValue(product, ProductEntity.class);
        entity = produtoRepository.save(productEntity);
        return objectMapper.convertValue(entity, Product.class);
    }

    public Product getBySku(Long sku){
        ProductEntity entity = produtoRepository.findBySku(sku);
        if(entity != null){
            entity = updateQuantity(entity);
            return objectMapper.convertValue(entity, Product.class);
        }
        return null;
    }

    public void deleteProduct(Long sku){
        ProductEntity entity = produtoRepository.findBySku(sku);
        if(entity != null){
            produtoRepository.delete(entity);
        }
    }

//    public Product update(Long sku, Product product){
//        ProductEntity entity = produtoRepository.findBySku(sku);
//        if(entity != null){
//            Product product1 = objectMapper.convertValue(entity, Product.class);
//        }
//    }

    private ProductEntity updateQuantity(ProductEntity productEntity){
        int quantityWarehouse = productEntity.getInventory().getWarehouses().stream().mapToInt(w -> w.getQuantity()).sum();
        productEntity.getInventory().setQuantity(quantityWarehouse);
        boolean isMarketable = productEntity.getInventory().getQuantity() > 0 ? true : false;
        productEntity.setMarketable(isMarketable);
        return productEntity;
    }
}
