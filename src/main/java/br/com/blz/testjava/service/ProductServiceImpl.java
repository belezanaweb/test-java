package br.com.blz.testjava.service;

import br.com.blz.testjava.dto.ProductDTO;
import br.com.blz.testjava.exceptions.ExistentProductException;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductRepository productRepository;

    List<Product> serviceProducts;

    @Override
    public Product createProduct(ProductDTO product) {
        logger.info(String.format("Criando produto %s", product));

        recoverProductsFromRepository();

        checkProductAlreadyRegistred(product.getSku());
        Product newProduct =
            Product.builder()
                .sku(product.getSku())
                .name(product.getName()).build();

        serviceProducts.add(newProduct);
        productRepository.setProducts(serviceProducts);

        logger.info(String.format("produto criado: %s ", newProduct));
        return newProduct;
    }

    @Override
    public Product findProductBySKU(Integer sku) {
        return null;
    }

    @Override
    public Product editProduct(ProductDTO product) {
        return null;
    }

    @Override
    public Product deleteProductBySku(Integer sku) {
        return null;
    }

    protected void recoverProductsFromRepository(){
        this.serviceProducts =
            Optional.ofNullable(productRepository.getProducts()).orElse(new ArrayList<>());
    }

    protected void checkProductAlreadyRegistred(Integer sku) throws RuntimeException{
        List<Product> produtosExistente =
            serviceProducts.stream()
                .filter(product -> product.getSku().equals(sku))
                .collect(Collectors.toList());
        if(! produtosExistente.isEmpty())
            throw new ExistentProductException("já existe um produto com o SKU informado.");
    }
}
