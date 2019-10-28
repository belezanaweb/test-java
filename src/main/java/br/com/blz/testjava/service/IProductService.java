package br.com.blz.testjava.service;

import java.text.ParseException;
import java.util.List;

import br.com.blz.testjava.dao.entity.Product;

public interface IProductService {

	Iterable<Product> findAll();

	Product findById(Long sku);

	Product create(Product product) throws ParseException;

	void delete(Long sku);

	Product update(Long sku, Product product) throws ParseException;

}
