package br.com.blz.produtos.apirest.view;



import br.com.blz.produtos.apirest.entity.EntityProduto;


public class ObterProdutoViewProdutoResponse {
	
	private Long sku;
	private String name;
	private ObterProdutoViewInventoryResponse inventory;
	private Boolean isMarketable;
	
	//-------------------------------------------
	public ObterProdutoViewProdutoResponse() {
		super();
	}

	public ObterProdutoViewProdutoResponse(Long sku, String name, ObterProdutoViewInventoryResponse inventory,
			Boolean isMarketable) {
		super();
		this.sku = sku;
		this.name = name;
		this.inventory = inventory;
		this.isMarketable = isMarketable;
	}
	
	public ObterProdutoViewProdutoResponse(EntityProduto produto) {
		super();
		if(produto!=null) {
			this.sku          = produto.getSku();
			this.name         = produto.getName();
			this.inventory    = new ObterProdutoViewInventoryResponse(produto.getWarehouses());
			this.isMarketable = (this.inventory.getQuantity() > 0) ? true : false;
		}
	}

	//-------------------------------------------

	public Long getSku() {
		return sku;
	}
	
	public void setSku(Long sku) {
		this.sku = sku;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ObterProdutoViewInventoryResponse getInventory() {
		return inventory;
	}
	
	public void setInventory(ObterProdutoViewInventoryResponse inventory) {
		this.inventory = inventory;
	}
	
	public Boolean isMarketable() {
		return isMarketable;
	}

	public void setMarketable(Boolean isMarketable) {
		this.isMarketable = isMarketable;
	}
	//-------------------------------------------
	
	
}
