package br.com.blz.testjava.usecases;

import br.com.blz.testjava.domains.Product;
import br.com.blz.testjava.gateways.http.converter.ProductResponseJSON;

public interface ProductCreate {

    ProductResponseJSON execute(Product product);

}
