package br.com.blz.testjava.dominio.enums;

public enum TypeWareHouse {

	ECOMMERCE(1, "ECOMMERCE"), 
	PHYSICAL_STORE(2, "PHYSICAL_STORE");

	private Integer cod;
	private String descricao;

	private TypeWareHouse(Integer cod, String descricao) {
			this.cod = cod;
			this.descricao = descricao;
		}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TypeWareHouse toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (TypeWareHouse x : TypeWareHouse.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
