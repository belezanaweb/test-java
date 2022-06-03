package br.com.blz.testjava.core.ports;

import br.com.blz.testjava.core.domain.Product;
import lombok.NonNull;

public interface SaveProductCachePort {

    void saveProduct(@NonNull Product product);

}
