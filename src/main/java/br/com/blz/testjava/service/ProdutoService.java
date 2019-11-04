package br.com.blz.testjava.service;

import br.com.blz.testjava.exception.ProdutoNegocioException;
import br.com.blz.testjava.exception.ProdutoNaoLocalizadoException;
import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    @Autowired
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto obterByChave(Integer sku) {
        return this.repository.getBySku(sku).orElseThrow(() -> new ProdutoNaoLocalizadoException(sku));
    }

    public void salvar(Produto product) {
        this.repository.getBySku(product.getSku()).ifPresent(s -> {
            throw new ProdutoNegocioException("Produto já cadastrado");
        });
        this.repository.save(product);
    }

    public void atualizar(Produto product) {
        this.obterByChave(product.getSku()); 
        this.repository.save(product);
    }

    public void delete(Integer sku) {
        this.obterByChave(sku);
        this.repository.delete(sku);
    }
}