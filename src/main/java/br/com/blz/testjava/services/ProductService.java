package br.com.blz.testjava.services;

import br.com.blz.testjava.exceptions.BusinessException;
import br.com.blz.testjava.models.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {

    //    Representação dos dados em memória
    private final Map<Long, Product> products;

    public ProductService() {
        this.products = new HashMap<>();
    }

    public void save(Product product) {
        this.validate(product);
        if (products.get(product.getSku()) != null) {
            throw new BusinessException("Produto existente");
        }
        this.products.put(product.getSku(), product);
    }

    public void update(Product product) {
        this.validate(product);
        Product productDB = products.get(product.getSku());
        if (productDB == null) {
            throw new BusinessException("Produto não encontrado");
        }

        products.replace(productDB.getSku(), product);

    }

    public void delete(Long sku) {
        products.remove(sku);
    }

    public Product findOne(Long sku) {
        Product productDB = this.products.get(sku);
        if (productDB != null) {
            Integer inventoryQty = 0;
            if (productDB.getInventory() != null) {
                inventoryQty = productDB.getInventory().calculateQuantity();
                productDB.getInventory().setQuantity(inventoryQty);
            }
            productDB.setMarketable(inventoryQty > 0);
        }
        return productDB;
    }

    private void validate(Product product) {
        if (product.getSku() == null) {
            throw new BusinessException("SKU é obrigatório");
        }
    }

}
