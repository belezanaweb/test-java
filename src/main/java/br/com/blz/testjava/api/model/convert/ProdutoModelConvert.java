package br.com.blz.testjava.api.model.convert;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.blz.testjava.api.model.ProdutoModel;
import br.com.blz.testjava.api.model.input.ProdutoInput;
import br.com.blz.testjava.model.Produto;

@Component
public class ProdutoModelConvert {

	@Autowired
	private ModelMapper modelMapper;

	public ProdutoModel toModel(Produto produto) {
		return modelMapper.map(produto, ProdutoModel.class);

	}

	public List<ProdutoModel> toCollectionModel(List<Produto> produtos) {
		return produtos.stream().map(produto -> toModel(produto)).collect(Collectors.toList());

	}
	
	public Produto fromModel(ProdutoInput produtoInput) {
		return modelMapper.map(produtoInput, Produto.class);

	}
	
	public void copyToDomainObject(ProdutoInput produtoInput,Produto produto) {
		modelMapper.map(produtoInput, produto);
	}
	
}
