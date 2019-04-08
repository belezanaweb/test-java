package br.com.blz.testjava.gateways;

import br.com.blz.testjava.domains.Product;

public interface ProductGateway {

    Product save(Product book);

    Boolean existsProductBySKU(Integer sku);

    Product findById(Integer sku);

    void deleteById(Integer sku);

}
