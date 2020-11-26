package br.com.blz.testjava.repository;

import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.validators.Validators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(Validators.class);

    private static Map<String, Product> products = new HashMap<>();

    public Product findProduct(String sku) {
        logger.info(String.format("Procurando produto [SKU: %s]", sku));
        return products.getOrDefault(sku, null);
    }

    public Product createProduct(Product product) {
        logger.info(String.format("Criando produto [SKU: %s]", product.getSku()));
        products.put(product.getSku(), product);
        return product;
    }

    public void deleteProduct(String sku) {
        logger.info(String.format("Removendo produto [SKU: %s]", sku));
        products.remove(sku);
    }

    public Product updateProduct(Product product) {
        String sku = product.getSku();
        logger.info(String.format("Atualizando produto [SKU: %s]", sku));
        products.put(sku, product);
        return product;
    }

}
