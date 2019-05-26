	package br.com.blz.testjava;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.models.InventoryEntity;
import br.com.blz.models.ProductEntity;
import br.com.blz.models.WarehouseEntity;
import br.com.blz.repository.ProductRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE) 
public class TestJavaApplicationTests {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	ProductRepository productDAO;

	@Test
	public void should_find_products_if_repository_isNot_empty() {
		Iterable<ProductEntity> products = productDAO.findAll();
		assertThat(products).isNotEmpty();
	}
	
	@Test
	  public void should_store_a_product() {
		
		//SETA WAREHOUSE 1
				WarehouseEntity warehouseEntity_x1 = new WarehouseEntity();
				warehouseEntity_x1.setLocality("SP");
				warehouseEntity_x1.setQuantity(12);
				warehouseEntity_x1.setType("ECOMMERCE");
				
				//SETA WAREHOUSE 2
				WarehouseEntity warehouseEntity_x2 = new WarehouseEntity();
				warehouseEntity_x2.setLocality("MOEMA");
				warehouseEntity_x2.setQuantity(3);
				warehouseEntity_x2.setType("PHYSICAL_STORE");
				
				//SETA IVENTORY 1 -> p/ WAREHOUSE 1 + WAREHOUSE 2
				InventoryEntity inventoryEntity_x1 = new InventoryEntity();
				inventoryEntity_x1.getWarehouses().add(warehouseEntity_x1);
				inventoryEntity_x1.getWarehouses().add(warehouseEntity_x2);
				
				//SETA PRODUCT
				ProductEntity productEntity = new ProductEntity();
				productEntity.setSku(new Long("99999"));
				productEntity.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
				
				//ASSOCIA IVENTORY
				productEntity.setInventory(inventoryEntity_x1);
		
				ProductEntity productEntityNew = productDAO.save(productEntity);
	 
	    assertThat(productEntityNew).hasFieldOrPropertyWithValue("sku", new Long("99999"));	    
	  }

}
