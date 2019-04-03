package br.com.blz.testjava.rest.model.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.blz.testjava.business.model.WharehouseBO;

@JsonPropertyOrder({ "locality", "quantity", "type"})
public class WharehouseResponse {

	private WharehouseBO wr;

	public WharehouseResponse(WharehouseBO wr) {
		this.wr = wr;
	}

	public String getLocality() {
		return this.wr.getLocality();
	}
	
	public Integer getQuantity() {
		return this.wr.getQuantity();
	}
	
	public String getType() {
		return this.wr.getType();
	}
	
}
