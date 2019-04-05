package br.com.blz.testjava.dto;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;

import br.com.blz.testjava.entity.Warehouse;
import io.swagger.annotations.ApiModelProperty;

public class InventoryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3685596060065205722L;

	@Expose(serialize = false)
	@ApiModelProperty(hidden = true)
	private Long id;
	
	@Expose(serialize = true)
	@ApiModelProperty(hidden = true)
	private Integer quantity;

	@Expose(serialize = true)
	@ApiModelProperty(hidden = false)
	List<Warehouse> warehouses;
}
