package br.com.blz.testjava.converter;

import br.com.blz.testjava.api.v1.model.ProductDTO;
import br.com.blz.testjava.domain.Product;

public interface IProductConverter extends IDomainConverter<ProductDTO, Product> {
}