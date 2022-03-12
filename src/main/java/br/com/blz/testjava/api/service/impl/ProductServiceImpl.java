package br.com.blz.testjava.api.service.impl;

import br.com.blz.testjava.api.domain.Product;
import br.com.blz.testjava.api.domain.Warehouse;
import br.com.blz.testjava.api.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    private final List<Product> products = new ArrayList<>();

    @Override
    public void save(Product product) {

        if(!Objects.isNull(findProductBySku(product.getSku()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um produto com o mesmo código sku!");
        }

        products.add(product);
    }

    @Override
    public Product findProductBySku(Integer sku) {

        Product productExists = products.stream()
            .filter(product -> Objects.equals(product.getSku(), sku))
            .findAny()
            .orElse(null);

        if(!Objects.isNull(productExists)) {
            List<Warehouse> warehouses = productExists.getInventory().getWarehouses();

            Integer quantity = warehouses.stream()
                .map(Warehouse::getQuantity)
                .reduce(0, Integer::sum);

            productExists.getInventory().setQuantity(quantity);
            productExists.setMarketable(quantity > 0);
        }
        return productExists;
    }

    @Override
    public void update(Product product, Integer sku) {
        Product productUpdate = findProductBySku(sku);

        if(!Objects.isNull(productUpdate)) {
            product.setSku(productUpdate.getSku());
            this.products.set(this.products.indexOf(productUpdate), product);
        }
    }

    @Override
    public void delete(Integer sku) {
        Product product = findProductBySku(sku);

        if(!Objects.isNull(product)) {
            this.products.remove(product);
        }
    }


}
