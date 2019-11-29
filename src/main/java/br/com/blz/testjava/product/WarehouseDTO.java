package br.com.blz.testjava.product;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value="Warehouse", description="Classe que representa o armazem")
public class WarehouseDTO {
	
	@NotNull
	@ApiModelProperty(value="Local onde o prodto está armazenado")
	private String locality;
	@NotNull
	@ApiModelProperty(value="Quantidade do produto no armazem")
    private Long quantity;
	@NotNull
	@ApiModelProperty(value="Tipo")
    private Type type;
    
    public static WarehouseDTO from(Warehouse warehouse) {

    	return WarehouseDTO.builder()
    			.locality(warehouse.getLocality())
    			.quantity(warehouse.getQuantity())
    			.type(warehouse.getType())
    			.build();
    }

	public Warehouse parse() {

		return Warehouse.builder()
				.locality(this.locality)
				.quantity(this.quantity)
				.type(this.type)
				.build();
	}
    

}
