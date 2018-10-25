package br.com.blz.testjava.model;

public enum TypeWarehouse {
	ECOMMERCE("Ecommerce"),
	PHYSICAL_STORE("Physical Store");
	
	private String descricao;
	
	TypeWarehouse(String descricao) {
		this.setDescricao(descricao);
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString() {
		return this.descricao;
	}
	
}
