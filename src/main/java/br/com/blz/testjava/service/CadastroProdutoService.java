package br.com.blz.testjava.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.blz.testjava.domain.repository.ProdutoRepository;
import br.com.blz.testjava.exception.NegocioException;
import br.com.blz.testjava.exception.ProdutoNaoEncontradoException;
import br.com.blz.testjava.model.Produto;

@Service
public class CadastroProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Transactional
	public Produto salvar(Produto produto) {
		Optional<Produto> produtoExiste = repository.findById(produto.getSku());
		if (!produtoExiste.isEmpty())
			throw new NegocioException("Dois produtos são considerados iguais se os seus skus forem iguais");
		return repository.save(produto);
	}

	@Transactional
	public Produto atualizar(Produto produto) {
		return repository.save(produto);
	}
	public Produto buscarOuFalhar(Long sku) {
		return repository.findById(sku).orElseThrow(() -> new ProdutoNaoEncontradoException(sku));
	}

	public void excluir(Long skuId) {
		try {
			repository.deleteById(skuId);
			repository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new ProdutoNaoEncontradoException(skuId);

		}
	}
}
