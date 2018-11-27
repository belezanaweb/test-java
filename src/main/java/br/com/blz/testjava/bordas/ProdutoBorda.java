package br.com.blz.testjava.bordas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.blz.testjava.MsgException;
import br.com.blz.testjava.controles.ProdutoControle;
import br.com.blz.testjava.entidades.db.Produto;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProdutoBorda {
  
  @Autowired
  private ProdutoControle produtoControle;
  
  // --------------------------------------
  
  
  @GetMapping("/produtos")
  public List<Produto> buscar(){
    return produtoControle.buscar();
  }
  
  @GetMapping("/produtos/{id}")
  public Produto buscarId(@PathVariable("id") int id){
    Produto p = produtoControle.buscar(id);
    if( p == null ) throw new MsgException("Não existe nenhum Produto com este ID/SKU!", null).httpStatus(404);
    return p;
  }
  
  @PostMapping("/produtos")
  public void criar(@RequestBody Produto produto){
    produtoControle.gravar(produto);
    log.info("Criado o Produto de ID/SKU: {}", produto.getSku());
  }
  
  @PutMapping("/produtos")
  public void atualizar(@RequestBody Produto produto){
    if( produto.getSku() == 0 ) throw new MsgException("Deve ser informado o ID/SKU do produto que será atualizado!", null);
    produtoControle.atualizar(produto);
    log.info("Atualizado o Produto de ID/SKU: {}", produto.getSku());
  }
  
  @DeleteMapping("/produtos/{id}")
  public void deletar(@PathVariable("id") int id){
    if( id == 0 ) throw new MsgException("Deve ser informado o ID/SKU do produto que será deletado!", null);
    produtoControle.deletar(id);
    log.info("Deletado o Produto de ID/SKU: {}", id);
  }
  
} 
