package br.com.blz.testjava.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WarehousesDTO {
    
	private String locality;
	private Long quantity;
	private String type;
}
