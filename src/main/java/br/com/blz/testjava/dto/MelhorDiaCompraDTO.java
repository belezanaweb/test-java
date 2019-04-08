package br.com.blz.testjava.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MelhorDiaCompraDTO implements Serializable {

	private static final long serialVersionUID = -3484082584665009662L;

	private Integer id;
	private Integer melhorDiaCompra;

}
