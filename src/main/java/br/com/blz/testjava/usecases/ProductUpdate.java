package br.com.blz.testjava.usecases;

import br.com.blz.testjava.domains.Product;
import br.com.blz.testjava.gateways.http.converter.ProductResponseJSON;

public interface ProductUpdate {

    ProductResponseJSON execute(Integer id, Product product);

}
