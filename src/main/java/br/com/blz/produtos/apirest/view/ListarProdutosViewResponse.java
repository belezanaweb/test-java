package br.com.blz.produtos.apirest.view;

import java.util.ArrayList;
import java.util.List;


import br.com.blz.produtos.apirest.entity.EntityProduto;

public class ListarProdutosViewResponse {

	private List<ObterProdutoViewProdutoResponse> obterProdutoViewProdutosResponse = new ArrayList<ObterProdutoViewProdutoResponse>();
	
	//-------------------------------------------
	public ListarProdutosViewResponse() {
		super();
	}

	public ListarProdutosViewResponse(List<EntityProduto> entityProdutos) {
		super();
		for (EntityProduto entityProduto : entityProdutos) {
			this.obterProdutoViewProdutosResponse.add(new ObterProdutoViewProdutoResponse(entityProduto));
		}
	}

	

	//-------------------------------------------
	public List<ObterProdutoViewProdutoResponse> getObterProdutoViewProdutosResponse() {
		return obterProdutoViewProdutosResponse;
	}

	public void setObterProdutoViewProdutosResponse(
			List<ObterProdutoViewProdutoResponse> obterProdutoViewProdutosResponse) {
		this.obterProdutoViewProdutosResponse = obterProdutoViewProdutosResponse;
	}
	//-------------------------------------------
	
}
