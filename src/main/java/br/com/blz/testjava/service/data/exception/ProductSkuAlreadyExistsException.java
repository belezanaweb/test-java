package br.com.blz.testjava.service.data.exception;

public class ProductSkuAlreadyExistsException extends  Exception{

    public ProductSkuAlreadyExistsException(){
        super("Produto ja criado com este sku!");
    }
}
