package br.com.testeJava.api.documents;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
"locality",
"quantity",
"type"
})
public class Warehouse {

	@JsonProperty("locality")
	private String locality;
	@JsonProperty("quantity")
	private Integer quantity;
	@JsonProperty("type")
	private String type;
		
	public Warehouse() {
	}

	public Warehouse(String locality, Integer quantity, String type) {
		super();
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
		}

		@JsonProperty("locality")
		public String getLocality() {
		return locality;
		}

		@JsonProperty("locality")
		public void setLocality(String locality) {
		this.locality = locality;
		}

		@JsonProperty("quantity")
		public Integer getQuantity() {
		return quantity;
		}

		@JsonProperty("quantity")
		public void setQuantity(Integer quantity) {
		this.quantity = quantity;
		}

		@JsonProperty("type")
		public String getType() {
		return type;
		}

		@JsonProperty("type")
		public void setType(String type) {
		this.type = type;
		}

		@Override
		public String toString() {
			return "Warehouse [locality=" + locality + ", quantity=" + quantity + ", type=" + type + "]";
		}
			
}