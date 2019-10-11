package br.com.blz.testjava.dto;

import java.io.Serializable;

import lombok.Data;
@Data
public class WarehouseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2386620018892878392L;
	private String locality;
	private Long quantity;
	private String type;
}
