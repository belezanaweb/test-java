package br.com.blz.testjava;

public interface IProductRepository {

     void save (ProductDTOIn productDTOIn);
     ProductDTOOut edit(ProductDTOIn productDTOIn);
     ProductDTOOut get (int productSku);
     void delete (int productSku);


}
