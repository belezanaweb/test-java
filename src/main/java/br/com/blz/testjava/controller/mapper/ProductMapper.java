package br.com.blz.testjava.controller.mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.util.Streams;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.entity.Product;
import br.com.blz.testjava.entity.Warehouse;

public class ProductMapper {

	String req;
	ObjectMapper mapper = new ObjectMapper().configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	
	public ProductMapper(String req) {
		super();
		this.req = req;
	}

	public Product product() throws JsonProcessingException, IOException {
		
		return Product.builder()
					.sku(req != null && mapper.readTree(req).findValue("sku") != null ? mapper.readTree(req).findValue("sku").asLong() : 0L)
					.name(req != null && mapper.readTree(req).findValue("name") != null ? mapper.readTree(req).findValue("name").asText() : null)
					.isMarketable(req != null && mapper.readTree(req).findValue("isMarketable") != null ? mapper.readTree(req).findValue("isMarketable").asBoolean() : true)
					.inventory(this.inventory())
				.build();
	}
	
	private Inventory inventory() throws JsonProcessingException, IOException {
		
		return Inventory.builder()
					.id(req != null && mapper.readTree(req).findValue("inventory.id") != null ? mapper.readTree(req).findValue("inventory.id").asLong() : 0L)
					.quantity(req != null && mapper.readTree(req).findValue("inventory").findValue("quantity") != null ? mapper.readTree(req).findValue("inventory").findValue("quantity").asInt() : 0)
					.warehouses(this.warehouses())
				.build();
	}
	
	private List<Warehouse> warehouses() throws JsonProcessingException, IOException {		
		List<Warehouse> warehouses = new ArrayList<Warehouse>();
		
		mapper.readTree(req).findValue("warehouses").elements().forEachRemaining(map -> {
			warehouses.add(Warehouse.builder()
					.id(map.findValue("id") != null ? map.findValue("id").asLong() : 0L)
					.locality(map.findValue("locality").asText())
					.quantity(map.findValue("quantity").asInt())
					.type(map.findValue("type").asText())
				.build());
		});
		
		return warehouses;
	}
}
