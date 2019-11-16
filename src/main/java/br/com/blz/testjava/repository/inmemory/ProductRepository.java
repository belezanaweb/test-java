package br.com.blz.testjava.repository.inmemory;

import java.util.Optional;

public interface ProductRepository<T> {
    
    T save(T t);
    
    Optional<T> findBySku(long sku);
    
    boolean remove(long sku);
    
    void clean();
}
