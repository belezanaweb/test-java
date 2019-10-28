package br.com.blz.testjava.dao.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.blz.testjava.dao.entity.Product;
import br.com.blz.testjava.dao.entity.ProductEntryPK;
public interface IProductRepository extends CrudRepository<Product, ProductEntryPK> {
}