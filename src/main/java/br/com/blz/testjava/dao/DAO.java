package br.com.blz.testjava.dao;

import br.com.blz.testjava.model.Product;

import java.util.HashMap;
import java.util.Map;

public abstract class DAO {

    private static Map<Long, Product> products;

    public static void insertProduct(Product product) throws Exception {
        if (products == null){
            products = new HashMap<Long, Product>();
        }

        if (products.get(product.getSku()) != null){
            throw new Exception("Produto já existe");
        }

        products.put(product.getSku(), product);
    }

    public static void updateProduct(Product product) throws Exception {
        if (products == null){
            throw new Exception("Nãqo existem produtos cadastrados");
        }
        Product prod = products.get(product.getSku());
        if (prod == null)
            throw new Exception("Produto não encontrado");
        else
            products.put(product.getSku(), product);
    }

    public static Product getProduct(Long sku) throws Exception {
        if (products == null){
            throw new Exception("Nãqo existem produtos cadastrados");
        }
        Product prod = products.get(sku);
        if (prod == null)
            throw new Exception("Produto não encontrado");
        else
            return prod;
    }

    public static void deleteProduct(Long sku){
        products.remove(sku);
    }
}
