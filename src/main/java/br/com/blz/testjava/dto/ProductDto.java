
package br.com.blz.testjava.dto;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sku",
    "name",
    "inventory",
})
public class ProductDto {
    @JsonProperty("sku")
    private Long sku;
    @JsonProperty("name")
    private String name;
    @JsonProperty("inventory")
    private InventoryDto inventory;
    
	public Long getSku() {
		return sku;
	}
	public void setSku(Long sku) {
		this.sku = sku;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public InventoryDto getInventory() {
		return inventory;
	}
	public void setInventory(InventoryDto inventory) {
		this.inventory = inventory;
	}
}