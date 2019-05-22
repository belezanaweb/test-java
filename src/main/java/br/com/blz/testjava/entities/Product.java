
package br.com.blz.testjava.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "sku",
    "name",
    "inventory",
    "isMarketable"
})
public class Product {
	
	/*
	 * Lista estática para persistência dos produtos em memória. 
	*/
    @JsonProperty("sku")
    @EqualsAndHashCode.Include
    private Long sku;
    @JsonProperty("name")
    private String name;
    @JsonProperty("inventory")
    private Inventory inventory;
    @JsonProperty("isMarketable")
    private Boolean isMarketable;
    
}
