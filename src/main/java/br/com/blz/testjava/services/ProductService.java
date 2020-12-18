package br.com.blz.testjava.services;

import br.com.blz.testjava.api.vos.ProductVO;
import br.com.blz.testjava.model.entities.Product;

public interface ProductService {

    Product findBySku(Long sku);

    Product save(ProductVO vo);

    boolean delete(Long sku);

    Product update(ProductVO vo, Long sku);
}
