package br.com.blz.testjava.dto;

import java.io.Serializable;
import java.util.List;

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
	List<WarehouseDTO> warehouses;
}
