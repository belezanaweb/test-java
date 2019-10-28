
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
public class ProductRespDto {
    @JsonProperty("sku")
    private Long sku;
    @JsonProperty("name")
    private String name;
    @JsonProperty("inventory")
    private InventoryRespDto inventory;
    private Boolean isMarketable;
    
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
	public InventoryRespDto getInventory() {
		return inventory;
	}
	public void setInventory(InventoryRespDto inventory) {
		this.inventory = inventory;
	}
	public Boolean getIsMarketable() {
		return isMarketable;
	}
	public void setIsMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
	}
}