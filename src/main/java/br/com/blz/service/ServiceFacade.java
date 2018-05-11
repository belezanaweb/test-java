package br.com.blz.service;

import java.util.List;

import br.com.blz.model.ProdutoModel;
import br.com.blz.persistencia.ProdutoDAO;


public class ServiceFacade {
	
	
	ProdutoDAO dao = new ProdutoDAO();

	public ProdutoModel get(Integer l) {
		return dao.get(l);
	}

	public List<ProdutoModel> get() {
		return dao.get();
	}

	public void add(ProdutoModel produtoNovo) {
		dao.add(produtoNovo);
		
	}

	public void update(ProdutoModel produtoEditado) {
		dao.update(produtoEditado);
		
	}

	public void delete(ProdutoModel produto) {
		dao.delete(produto);
	}

}
