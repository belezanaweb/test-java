package br.com.blz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.entity.Produto;
import br.com.blz.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService{
	
	@Autowired
	private ProdutoRepository produtoRespository;
	
	@Override
    public Produto getById(Integer id) {
        return produtoRespository.findById(id).orElse(null);
    }

    @Override
	public Produto save(Produto produto) {
        if(getById(produto.getSku()) == null){
        	return produtoRespository.save(produto);
        }
    	return null;
    }
    
    @Override
    public Produto edit(Produto produto) {
        //if(getById(produto.getSku()) != null){
        	return produtoRespository.save(produto);
        //}
    	//return null;
    }

    @Override
    public void deleteById(Integer id) {
    	produtoRespository.deleteById(id);
    }


}

