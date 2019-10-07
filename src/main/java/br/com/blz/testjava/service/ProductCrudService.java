package br.com.blz.testjava.service;

import br.com.blz.testjava.custom.exception.ProductConflictException;
import br.com.blz.testjava.custom.exception.ProductNotFoundException;
import br.com.blz.testjava.custom.messages.MessageConfig;
import br.com.blz.testjava.custom.messages.MsgsContants;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCrudService {

    @Autowired
    private ProductsRepository repository;

    @Autowired
    MessageConfig messages;

    public final void createProduct(Product product){
        if (repository.existsById(product.getSku()))
            throw new ProductConflictException(messages.get(MsgsContants.CONFLICT_INSERT_PRODUCTS));

        repository.save(product);
    }

    public final Product getProduct(int codSku){

        Optional<Product> product = repository.findById(codSku);

        if (!product.isPresent())
            throw new ProductNotFoundException(messages.get(MsgsContants.PRODUCT_NOT_FOUND));

        int quantity = product.get().getInventory().getWarehouses().stream().mapToInt(warehouse -> warehouse.getQuantity())
            .reduce(0, (a, b) -> a + b);

        product.get().getInventory().setQuantity(quantity);

        product.get().setMarketable(quantity > 0);

        return product.get();
    }

    public final void updateProduct(int skuCode, Product product){

        if (!repository.existsById(skuCode))
            throw new ProductNotFoundException(MsgsContants.PRODUCT_NOT_FOUND);

        repository.save(product);
    }

    public final void deleteProduct(int sku){

        repository.deleteById(sku);
    }
}
