package br.com.blz.testjava.dto;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Getter
@Setter
public class ProdutoDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6629390556943699204L;

	@Expose(serialize = false)
	@ApiModelProperty(hidden = true)
	private Long id;
	
	@Expose(serialize = true)
	@ApiModelProperty(hidden = false)
	private String sku;
	
	@Expose(serialize = true)
	@ApiModelProperty(hidden = false)
	private String name;
	
	@Expose(serialize = true)
	@ApiModelProperty(hidden = false)
	private InventoryDTO inventory;
	
	@Expose(serialize = true)
	@ApiModelProperty(hidden = true)
	private Boolean isMarketable;

}
