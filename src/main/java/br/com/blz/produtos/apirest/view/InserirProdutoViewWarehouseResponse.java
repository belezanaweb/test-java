package br.com.blz.produtos.apirest.view;


public class InserirProdutoViewWarehouseResponse {

	
	private String locality;
	private int quantity;
	private String type;
	
	//-------------------------------------------
	public InserirProdutoViewWarehouseResponse() {
		super();
	}
	
	public InserirProdutoViewWarehouseResponse(String locality, int quantity, String type) {
		super();
		this.locality = locality;
		this.quantity = quantity;
		this.type = type;
	}

	//-------------------------------------------

	public String getLocality() {
		return locality;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getType() {
		return type;
	}
	//-------------------------------------------
	
	
}
