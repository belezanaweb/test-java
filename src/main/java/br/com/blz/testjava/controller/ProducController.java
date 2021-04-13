package br.com.blz.testjava.controller;

import br.com.blz.testjava.entity.Inventory;
import br.com.blz.testjava.service.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/inventories")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @GetMapping("{sku}")
    @ResponseStatus(HttpStatus.OK)
    public Inventory findBySku(@Valid @PathVariable Integer sku) {
        return service.findByProduct(Long.valueOf(sku));
    }

    @PutMapping("{sku}")
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory alterInventory(@Valid @PathVariable Integer sku, @RequestBody Inventory inventory) {
        service.findByProduct(Long.valueOf(sku));
        return service.alterInventory(inventory);
    }

    @DeleteMapping("{sku}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInventory(@Valid @PathVariable Integer sku) {
        service.deleteInventory(Long.valueOf(sku));
    }
}
