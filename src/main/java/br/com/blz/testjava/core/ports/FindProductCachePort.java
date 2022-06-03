package br.com.blz.testjava.core.ports;

import br.com.blz.testjava.core.domain.Product;
import lombok.NonNull;

public interface FindProductCachePort {

    Product findProduct(@NonNull String sku);

}
