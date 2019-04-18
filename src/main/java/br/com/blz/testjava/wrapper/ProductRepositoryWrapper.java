package br.com.blz.testjava.wrapper;

import br.com.blz.testjava.products.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductRepositoryWrapper implements CrudRepository<Product, Long> {

    protected Map<Long, Product> cache = new HashMap();

    @Override
    public <S extends Product> S save(S s) {
        cache.put(s.getSku(), s);
        return s;
    }

    @Override
    public <S extends Product> Iterable<S> save(Iterable<S> iterable) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public Product findOne(Long aLong) {
        return cache.get(aLong);
    }

    @Override
    public boolean exists(Long aLong) {
        return cache.containsKey(aLong);
    }

    @Override
    public Iterable<Product> findAll() {
        return cache.values();
    }

    @Override
    public Iterable<Product> findAll(Iterable<Long> iterable) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public long count() {
        return cache.size();
    }

    @Override
    public void delete(Long aLong) {
        cache.remove(aLong);
    }

    @Override
    public void delete(Product product) {
        cache.remove(product.getSku());
    }

    @Override
    public void delete(Iterable<? extends Product> iterable) {
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public void deleteAll() {
        cache.clear();
    }
}
