package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    public Product insert(Product product){
        this.products.add(product);
        return product;
    }

    public Product update(Product updatedProduct, long sku){

        List<Product> updatedProducts = new ArrayList<>();

        for(Product product: this.products){

            if(product.getSku() == sku){
                updatedProducts.add(updatedProduct);
            }else{
                updatedProducts.add(product);
            }
        }

        this.products = updatedProducts;
        return updatedProduct;
    }

    public List<Product> delete(Long sku){

        List<Product> savedProducts = new ArrayList<>();

        for(Product product: this.products){

            if(product.getSku() != sku){
                savedProducts.add(product);
            }
        }

        this.products = savedProducts;
        return this.products;
    }

    public Product getBySku(Long sku){

        for(Product product: this.products){

            if(product.getSku() == sku){
                return product;
            }
        }
        return null;
    }

    public List<Product> getAll(){
        return this.products;
    }

}
