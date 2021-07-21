package br.com.blz.testjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mensagem {
	
	public Mensagem(int codigo, String msg) {
		this.codigo = codigo;
		this.msg = msg;
	}
	@JsonProperty("codigo")
	private int codigo;
	@JsonProperty("mensagem")
	private String msg;

}
