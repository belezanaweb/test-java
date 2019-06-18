package br.com.blz.testjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.model.Warehouses;
/**
 * @author Evandro Lopes da Rocha (evandro.esw@gmail.com)
 * @date 13/06/2019.
 */
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	Produto findBySku(long sku);
	void saveAndFlush(Warehouses warehouses);
}
