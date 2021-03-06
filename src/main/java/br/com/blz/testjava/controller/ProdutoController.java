package br.com.blz.testjava.controller;

import br.com.blz.testjava.entity.dto.ProdutoEntradaDTO;
import br.com.blz.testjava.entity.dto.ProdutoRetornoDTO;
import br.com.blz.testjava.service.ProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;
    Logger logger = LoggerFactory.getLogger(ProdutoService.class);

    @GetMapping("/{sku}")
    public ResponseEntity<ProdutoRetornoDTO> getProduto(@PathVariable Long sku) {
        try{
            return ResponseEntity.ok(produtoService.getProduto(sku));
        } catch (Exception e){
            logger.error("Erro ao buscar o produto");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProdutoRetornoDTO> salvar(@RequestBody ProdutoEntradaDTO dto, UriComponentsBuilder uriBuilder){
        try {
            produtoService.save(dto);
            URI uri = uriBuilder.path("/produto/{sku}").buildAndExpand(dto.getSku()).toUri();
            return ResponseEntity.created(uri).body(produtoService.getProduto(dto.getSku()));
        } catch (Exception e){
            logger.error("Erro ao salvar o produto");
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{sku}")
    public ResponseEntity<ProdutoRetornoDTO> atualizar(@RequestBody ProdutoEntradaDTO dto, @PathVariable Long sku) {
        try{
            return ResponseEntity.ok(produtoService.atualizar(sku, dto));
        } catch (Exception e){
            logger.error("Erro ao atualizar o produto");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<?> deletar(@PathVariable Long sku) {
        try{
            produtoService.deletar(sku);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            logger.error("Erro ao deletar o produto");
            return ResponseEntity.notFound().build();
        }
    }

}
