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
public class WarehouseDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3818642567018571757L;

	@Expose(serialize = false)
	@ApiModelProperty(hidden = true)
	private Long id;
	
	@Expose(serialize = true)
	@ApiModelProperty(hidden = false)
	private String locality;
	
	@Expose(serialize = true)
	@ApiModelProperty(hidden = false)
	private Integer quantity;
	
	@Expose(serialize = true)
	@ApiModelProperty(hidden = false)
	private String type;
	
}
