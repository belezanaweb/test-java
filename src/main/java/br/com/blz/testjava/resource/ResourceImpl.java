package br.com.blz.testjava.resource;

import br.com.blz.testjava.entity.Produto;
import br.com.blz.testjava.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@Transactional
@Slf4j
public class ResourceImpl implements Serializable {

    private static final long serialVersionUID = -6595160708450117682L;
    @Autowired
    private ProdutoRepository produtoRepository;


    public ResponseEntity<?> salvar(Produto produto) {

        int quantity = produto.getInventory()
                                .getWarehouses()
                                .stream()
                                .mapToInt(inventario -> inventario.getQuantity()).sum();

        produto.getInventory().setQuantity(quantity);
            try{
                    if(produtoRepository.exists(produto.getSku())) {
                        throw new Exception("Produto ja Existe");
                    }
            } catch (Exception e) {
                log.error(e.getMessage(),e);
                return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
    }
     return new ResponseEntity(produtoRepository.save(produto), HttpStatus.OK);
    }

    public ResponseEntity<?> editar(Produto produto) {
       return  new ResponseEntity(produtoRepository.save(produto), HttpStatus.OK);
    }

    public ResponseEntity<?> deletar(Long sku) {
        return new ResponseEntity(produtoRepository.deletar(sku), HttpStatus.OK);
    }

    public ResponseEntity<?> recuperar(Long sku) {
            try {
                 if(produtoRepository.buscaProdutoJaDisponivel(sku)){
                     throw new Exception("Produto já Disponivel");
                 }
            } catch (Exception e) {
                    log.error(e.getMessage(),e);
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
        final Integer retorno = produtoRepository.recuperarProduto(sku);
        final Produto produto = produtoRepository.findBysku(sku).get();

        int quantity = produto.getInventory()
            .getWarehouses()
            .stream()
            .mapToInt(inventario -> inventario.getQuantity()).sum();

            produto.getInventory().setQuantity(quantity);

        return new ResponseEntity(produto, HttpStatus.OK) ;
    }
}
