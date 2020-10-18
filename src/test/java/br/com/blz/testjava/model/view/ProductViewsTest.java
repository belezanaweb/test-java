package br.com.blz.testjava.model.view;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.testjava.model.dto.InventoryItemDTO;
import br.com.blz.testjava.model.dto.InventoryItemsDTO;
import br.com.blz.testjava.model.dto.ProductDTO;
import br.com.blz.testjava.model.enums.StockType;
import br.com.blz.testjava.model.view.ProductViews.ProductRead;
import br.com.blz.testjava.model.view.ProductViews.ProductWrite;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class ProductViewsTest {
	
	private static final String SKU_DEFAULT = "SKU-0001";
	
	@Autowired
	private ObjectMapper mapper;

	@Test
	void testProductReadSerialize() {
		try {
			ProductDTO product = this.getProductDefault();
			String json = mapper.writerWithView(ProductRead.class)
					.writeValueAsString(product);
			assertThat(json, containsString("isMarketable"));
		} catch (JsonProcessingException e) {
			log.error("Fail in serialize the product  {}", e);
		}
	}
	
	@Test
	void testProductWriteSerialize() {
		try {
			ProductDTO product = this.getProductDefault();
			String json = mapper.writerWithView(ProductWrite.class)
					.writeValueAsString(product);
			assertThat(json, not(containsString("isMarketable")));
		} catch (JsonProcessingException e) {
			log.error("Fail in serialize the product  {}", e);
		}
	}
	
	@Test
	void testProductWriteDeserialize() {
		try {
			ProductDTO product = this.getProductDefault();
			product.setMarketable(true);
			
			String json = mapper.writerWithView(ProductRead.class)
					.writeValueAsString(product);
			
			assertThat(json, containsString("\"isMarketable\":true"));
			
			product = mapper.readerWithView(ProductWrite.class)
					.forType(ProductDTO.class)
					.readValue(json);
			
			assertFalse(product.isMarketable());
		} catch (JsonProcessingException e) {
			log.error("Fail in serialize the product  {}", e);
		}
	}
	
	private ProductDTO getProductDefault() {
		ProductDTO product = new ProductDTO();
		product.setSKU(SKU_DEFAULT);
		product.setName("first product");
		
		List<InventoryItemDTO> items = new ArrayList<>();
		
		InventoryItemDTO item = new InventoryItemDTO();
		
		item.setLocality("sp");
		item.setQuantity(10L);
		item.setType(StockType.PHYSICAL_STORE);
		items.add(item);
		
		item = new InventoryItemDTO();
		item.setLocality("RJ");;
		item.setQuantity(300L);
		item.setType(StockType.ECOMMERCE);
		items.add(item);
	
		InventoryItemsDTO inventoryItemsDTO = new InventoryItemsDTO();
		
		inventoryItemsDTO.setItems(items);
		
		product.setInventory(inventoryItemsDTO);
		
		return product;
		
	}

}
