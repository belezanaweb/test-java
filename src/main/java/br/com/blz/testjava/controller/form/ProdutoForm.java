package br.com.blz.testjava.controller.form;

import br.com.blz.testjava.model.Produto;

public class ProdutoForm {

	private long sku;

	private String name;

	private WarehousesForm inventory;

	public long getSku() {
		return sku;
	}

	public void setSku(long sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WarehousesForm getInventory() {
		return inventory;
	}

	public void setInventory(WarehousesForm inventory) {
		this.inventory = inventory;
	}

	public Produto converterProduto() {

		// prod.setInventory(inventory.converterWarehouses(Warehouses warehouses));

		return new Produto(sku, name, inventory.converterWharehouses());
	}

	// public Prsoduto converter(Produto produtoRepository) {
//		Curso curso = produtoRepository.findByNome(nomeCurso);
//		return new Produto(titulo, mensagem, curso);
//	}

}
