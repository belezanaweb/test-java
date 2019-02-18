package br.com.blz.testjava.respositories;

import br.com.blz.testjava.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author aislan
 */
public interface ProductRepository extends JpaRepository<Product, Long>{
    Product findBySku(long sku);
}
