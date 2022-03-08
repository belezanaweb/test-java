package br.com.blz.testjava.repository;

import br.com.blz.testjava.dto.in.ProductDTOIn;
import br.com.blz.testjava.dto.out.ProductDTOOut;
import br.com.blz.testjava.entity.Product;

import java.util.Optional;

public interface IProductRepository {

     void save (Product product);
    Product edit(Product product);
    Product get (int productSku);
     void delete (int productSku);


}
