package br.com.blz.testjava.service.data;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.service.data.exception.ProductSkuAlreadyExistsException;
import br.com.blz.testjava.service.data.exception.SkuNotProvidedException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ProductDataHandlerImpl implements ProductDataHandler {

    final Map<Long, Product> productMap = new HashMap<Long, Product>();

    @Override
    public Product addProduct(final Product product) throws SkuNotProvidedException, ProductSkuAlreadyExistsException {
        if (Objects.isNull(product.getSku())) {
            throw new SkuNotProvidedException();
        }
        if (Objects.nonNull(productMap.get(product.getSku()))) {
            throw new ProductSkuAlreadyExistsException();
        }
        productMap.put(product.getSku(), product);
        return product;
    }

    @Override
    public Product updateProduct(final Product newProduct) {
        Optional<Product> optionalProduct = Optional.ofNullable(productMap.get(newProduct.getSku()));
        if (optionalProduct.isPresent()) {
            return productMap.put(newProduct.getSku(), newProduct);
        }
        return null;
    }

    @Override
    public Product getProduct(final Long sku) {
        final Product product = productMap.get(sku);
        final boolean hasInventory = Objects.nonNull(product)
            && Objects.nonNull(product.getInventory())
            && Objects.nonNull(product.getInventory().getWarehouses());
        final boolean hasProduct = Objects.nonNull(product);

        if (hasProduct) {
            product.setIsMarketable(Boolean.FALSE);
            AtomicLong atomicLong = new AtomicLong(0);
            if (hasInventory) {
                product.getInventory().getWarehouses().forEach(warehouse ->
                    atomicLong.set(warehouse.getQuantity() + atomicLong.get())
                );
                product.getInventory().setQuantity(Long.valueOf(atomicLong.get()));
                if (atomicLong.get() > 0) {
                    product.setIsMarketable(Boolean.TRUE);
                }
            }
        }
        return productMap.get(sku);
    }

    @Override
    public boolean removeProduct(final Long sku) {
        return Objects.nonNull(productMap.remove(sku));
    }

}
