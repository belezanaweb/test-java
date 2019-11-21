/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.blz.testjava;

import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.service.ProdutoService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Zupper
 */
@RestController
public class ProdutoResource {
  
    List<Produto> produtos = new ArrayList<>();
    
    @Autowired
    private ProdutoService produtoService;
    
  public ProdutoResource() {
    
  }
 
  
  @RequestMapping(value = "/produtos", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> insert(@Valid @RequestBody Produto produto) {
    
    produtoService.inserir(produto);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
  @RequestMapping(value = "/produtos/{sku}", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> editar(@PathVariable("sku") Integer sku, @Valid @RequestBody Produto produto) {
    
    produtoService.atualizar(sku, produto);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
  @RequestMapping(value = "/produtos", method = RequestMethod.GET)
  public ResponseEntity<List<Produto>> listar() {
    return new ResponseEntity<>(produtoService.listar(), HttpStatus.OK);
  }
  
  @RequestMapping(value = "/produtos/{sku}", method = RequestMethod.GET)
  public ResponseEntity<Produto> buscar(@PathVariable("sku") Integer sku) {
   
    Produto produto = produtoService.buscarItem(sku);
   
   if (produto == null) {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
   
  return new ResponseEntity<>(produto, HttpStatus.OK);
 }
  
  @RequestMapping(value = "/produtos/{sku}", method = RequestMethod.DELETE)
  public ResponseEntity<Produto> delete(@PathVariable("sku") Integer sku) {
    
   produtoService.remover(sku);
   return new ResponseEntity<>(HttpStatus.OK);
  }

   
}
