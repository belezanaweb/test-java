package br.com.blz.testjava.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blz.testjava.dto.ProdutoDTO;
import br.com.blz.testjava.dto.ProdutoSalvamentoDTO;
import br.com.blz.testjava.mapper.ProdutoMapper;
import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.repository.IProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private IProdutoRepository produtoRepository;

	public ProdutoDTO save(ProdutoSalvamentoDTO produtoDTO) {

		Produto produto = ProdutoMapper.toEntity(produtoDTO);
		produtoRepository.save(produto);

		return ProdutoMapper.toProdutoDTO(produto);
	}

	public ProdutoDTO findBySku(Long sku) {
		Produto produto = produtoRepository.findBySku(sku).orElseThrow(() -> new RuntimeException());

		return ProdutoMapper.toProdutoDTO(produto);
	}

	public void delete(Long sku) {
		produtoRepository.deleteProduto(sku);
	}

}
