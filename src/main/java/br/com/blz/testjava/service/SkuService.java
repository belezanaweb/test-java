package br.com.blz.testjava.service;

import br.com.blz.testjava.exception.SkuAlreadyExistsException;
import br.com.blz.testjava.exception.SkuNotFoundException;
import br.com.blz.testjava.model.Product;

public interface SkuService {

	void create(Product product) throws SkuAlreadyExistsException;

	void update(Product product) throws SkuNotFoundException;

	void remove(Long skuId) throws SkuNotFoundException;

	Product read(Long skuId) throws SkuNotFoundException;

}
