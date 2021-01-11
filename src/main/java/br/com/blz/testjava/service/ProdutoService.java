package br.com.blz.testjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dto.ProdutoDTO;
import br.com.blz.testjava.dto.ProdutoSalvamentoDTO;
import br.com.blz.testjava.mapper.ProdutoMapper;
import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.model.exception.ExistingProductException;
import br.com.blz.testjava.model.exception.ObjectNotFoundException;
import br.com.blz.testjava.repository.IProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private IProdutoRepository produtoRepository;

	public ProdutoDTO save(ProdutoSalvamentoDTO produtoDTO) throws ExistingProductException {

		Produto produto = ProdutoMapper.toEntity(produtoDTO);
		produtoRepository.save(produto);

		return ProdutoMapper.toProdutoDTO(produto);
	}

	public ProdutoDTO findBySku(Long sku) throws ObjectNotFoundException {
		Produto produto = produtoRepository.findBySku(sku).orElseThrow(() -> new ObjectNotFoundException(sku));

		return ProdutoMapper.toProdutoDTO(produto);
	}

	public void delete(Long sku) {
		produtoRepository.deleteProduto(sku);
	}

}
