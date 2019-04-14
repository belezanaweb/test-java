package br.com.blz.testjava.usecases;

import br.com.blz.testjava.dto.request.ProductRequestDTO;
import br.com.blz.testjava.dto.response.ProductResponseDTO;

public interface ProductUseCase {

    void create(ProductRequestDTO body);
    void delete(Integer sku);
    ProductResponseDTO getBySku(Integer sku);
    void update(ProductRequestDTO body, Integer sku);
}
