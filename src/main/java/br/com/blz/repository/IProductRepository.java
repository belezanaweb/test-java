package br.com.blz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.blz.dto.ProductDTO;
import br.com.blz.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
	
//	
	@Query(value ="Select  new br.com.blz.dto.ProductDTO(p.name, p.sku, sum(w.quantity),(sum(w.quantity)>0) ) from Product p join p.warehouses w where p.sku=:sku ")
	public ProductDTO findByIdReturnDTO(@Param("sku") Long sku);
	
	@Query(value ="Select * from product p inner join warehouse w on w.product_sku=p.sku where p.sku=:sku ",nativeQuery = true)
	public List<Product> findId(Long sku);

}
