package br.com.blz.testjava.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Produto {

	@NotEmpty
	private String sku;
	
	@NotEmpty
	private String name;

	@NotEmpty
	private Inventory inventory;
	
	private Boolean isMarketable;
	

	
}
