package br.com.blz.testjava.service;

import br.com.blz.testjava.api.v1.model.ProductDTO;

public interface IProductService {
	ProductDTO findOne(final Long sku);
	ProductDTO create(final ProductDTO newProduct);
	void delete(final Long sku);
	ProductDTO update(final Long sku, final ProductDTO newProduct);
	void deleteAll();
}
