package br.com.blz.testjava.service;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepositoryImpl repository;

    public ProductService( ProductRepositoryImpl repository){
        this.repository = repository;
    }

    public void updateProduct(final Product product) {
        repository.update(product);
    }

    public void insertProduct(final Product product) {
        repository.insert(product);
    }

    public void deleteProduct(final Integer sku) {
        repository.delete(sku);
    }

    public Product findBySku(final Integer sku) {
       Product product = repository.findBySku(sku);
       product.getInventory().setQuantity(product.getInventory().getWarehouses().stream()
                                                                               .filter(w-> w.getQuantity()!=null)
                                                                               .mapToInt(w -> w.getQuantity())
                                                                               .sum());
       product.setIsMarketable(product.getInventory().getQuantity() > 0);

       return product;
    }
}
