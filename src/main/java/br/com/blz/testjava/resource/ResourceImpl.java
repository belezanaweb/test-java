package br.com.blz.testjava.resource;

import br.com.blz.testjava.entity.Produto;
import br.com.blz.testjava.entity.Warehouses;
import br.com.blz.testjava.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ResourceImpl {

    @Autowired
    private ProdutoRepository produtoRepository;


    public Produto salvar(Produto produto) {

        int quantity = produto.getInventory()
                                .getWarehouses()
                                .stream()
                                .mapToInt(inventario -> inventario.getQuantity()).sum();

        produto.getInventory().setQuantity(quantity);
        try {
                if(produtoRepository.exists(produto.getSku())) {
                    throw new Exception("Produto nao Existe");
                }
            } catch (Exception e) {
            log.error(e.getMessage(),e);
        }

    return produtoRepository.save(produto);
    }

    public Produto editar(Produto produto) {
       return  produtoRepository.save(produto);
    }

    public void deletar(Long sku) {
        produtoRepository.deletar(sku);
    }

    public Produto recuperar(Long skul) {
        final Produto produto = produtoRepository.recuperarProduto(skul);

        int quantity = produto.getInventory()
            .getWarehouses()
            .stream()
            .mapToInt(inventario -> inventario.getQuantity()).sum();

        produto.getInventory().setQuantity(quantity);
        return produto;
    }
}
