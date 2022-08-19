package br.com.blz.testjava.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import br.com.blz.testjava.entity.ProductEntity;

@Repository
public class ProductRepository {
	
	private static final Map<Long, ProductEntity> PERSISTENCE_MEMORY = new ConcurrentHashMap<>();
	
	public List<ProductEntity> findAll() {
		 return PERSISTENCE_MEMORY.values().stream().collect(Collectors.toList());
	}
	
	public Optional<ProductEntity> findBySku(Long sku) {
		 return PERSISTENCE_MEMORY.values().stream().filter(p -> p.getSku().equals(sku)).findFirst();
	}

	public void save(ProductEntity entity) {
		PERSISTENCE_MEMORY.put(entity.getSku(), entity);
	}
	
	public boolean delete(Long sku) {
		 return PERSISTENCE_MEMORY.remove(sku) != null;
	}	
}
