package br.com.blz.testjava.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.blz.testjava.entities.Sku;
import br.com.blz.testjava.service.SkuService;

@RestController
@RequestMapping(value = "/sku")
public class SkuController {

    @Autowired
    private SkuService skuService;

    @PostMapping
    public ResponseEntity<Sku> insertSku(@RequestBody Sku sku) {
        sku = skuService.insert(sku);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(sku.getSku()).toUri();
        return ResponseEntity.created(uri).body(sku);

    }

    @PutMapping(value = "/{idSku}")
    public ResponseEntity<Sku> updateSku(@PathVariable Long idSku, @RequestBody Sku sku) {
        sku = skuService.update(idSku, sku);
        return ResponseEntity.ok().body(sku);
    }

    @GetMapping(value = "/{idSku}")
    public ResponseEntity<Sku> getAllSku(@PathVariable Long idSku) {
        Sku sku = skuService.findIdSku(idSku);
        return ResponseEntity.ok().body(sku);
    }

    @GetMapping
    public ResponseEntity<List<Sku>> getAllSku() {
        List<Sku> sku = skuService.findAllSku();
        return ResponseEntity.ok().body(sku);
    }

    @DeleteMapping(value = "/{idSku}")
    public ResponseEntity<Void> deleteSku(@PathVariable Long idSku) {
        skuService.delete(idSku);
        return ResponseEntity.noContent().build();
    }

}
