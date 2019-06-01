package br.com.blz.testjava.model;


import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;


public interface ProdutoRepository extends CrudRepository<Produto, Integer> {
	
    @Transactional
    Long deleteBySku(Integer sku);
    
}
