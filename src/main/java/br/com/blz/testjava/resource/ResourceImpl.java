package br.com.blz.testjava.resource;

import br.com.blz.testjava.entity.Produto;
import br.com.blz.testjava.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@Slf4j
public class ResourceImpl implements Serializable {

    private static final long serialVersionUID = -6595160708450117682L;
    @Autowired
    private ProdutoRepository produtoRepository;


    public ResponseEntity salvar(Produto produto) {

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

    return new ResponseEntity(produtoRepository.save(produto), HttpStatus.ACCEPTED);
    }

    public Produto editar(Produto produto) {
       return  produtoRepository.save(produto);
    }

    public void deletar(Long sku) {
        produtoRepository.deletar(sku);
    }

    public Produto recuperar(Long skul) {
        try {
             if(produtoRepository.buscaProdutoJaDisponivel(skul)){
                 throw new Exception("Produto já Disponivel");
             }
        } catch (Exception e) {
                log.error(e.getMessage(),e);
        }
        final Produto produto = produtoRepository.recuperarProduto(skul);

        int quantity = produto.getInventory()
            .getWarehouses()
            .stream()
            .mapToInt(inventario -> inventario.getQuantity()).sum();

        produto.getInventory().setQuantity(quantity);

        return produto;
    }
}
