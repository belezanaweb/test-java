package br.com.testeJava.api.documents;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.testeJava.api.documents.Inventory;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@SuppressWarnings("unused")
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"sku",
"name",
"inventory",
"isMarketable"
})
public class Product {

	@Id
	@JsonProperty("sku")
	private String sku;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("inventory")
	private Inventory inventory;
	
	@JsonProperty("isMarketable")
	private Boolean isMarketable;
		
	public Product() {
		
	}
	
	public Product(String sku, String name, Inventory inventory, Boolean isMarketable) {
		super();
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
		this.isMarketable = isMarketable;
	}

	@JsonProperty("sku")
	public String getSku() {
		return sku;
	}

	@JsonProperty("sku")
	public void setSku(String sku) {
		this.sku = sku;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("inventory")
	public Inventory getInventory() {
		return inventory;
	}

	@JsonProperty("inventory")
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	@JsonProperty("isMarketable")
	public Boolean getIsMarketable() {
		return isMarketable;
	}

	@JsonProperty("isMarketable")
	public void setIsMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sku == null) ? 0 : sku.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (sku == null) {
			if (other.sku != null)
				return false;
		} else if (!sku.equals(other.sku))
			return false;
		return true;
	}
			
	
}