package br.com.blz.testjava.resource;

import br.com.blz.testjava.entity.Produto;
import br.com.blz.testjava.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ResourceImpl {

    @Autowired
    private ProdutoRepository produtoRepository;


    public Produto salvar(Produto produto) {
        try {
                final boolean produtoIgual = produtoRepository.findById(produto.getSku()) == null ? true : false;
                if(produtoIgual) {
                    throw new Exception("Produto Já existente");
                }
            } catch (Exception e) {
                e.getMessage();
                e.printStackTrace();
        }

    return produtoRepository.save(produto);
    }

    public Produto editar(Produto produto) {
       return  produtoRepository.save(produto);
    }

    public void deletar(Produto produto) {
        produtoRepository.delete(produto);
    }
}
