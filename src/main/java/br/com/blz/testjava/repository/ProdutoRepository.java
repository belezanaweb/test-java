package br.com.blz.testjava.repository;

import br.com.blz.testjava.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProdutoRepository {
    private static Map<Integer, Produto> PRODUCTS = new HashMap<Integer, Produto>();

    public void save(Produto product) {
        PRODUCTS.put(product.getSku(), product);
    }

    public Optional<Produto> getBySku(Integer sku) {
        return Optional.ofNullable(PRODUCTS.get(sku));
    }

    public void delete(Integer sku) {
        PRODUCTS.remove(sku);
    }
}