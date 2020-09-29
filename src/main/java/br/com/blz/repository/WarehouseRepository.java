package br.com.blz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.blz.model.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

	@Query(value ="Select w.* from warehouse w inner join product p on w.product_sku=p.sku where p.sku=:sku ",nativeQuery = true)
	List<Warehouse> findBySKU(Long sku);

}
