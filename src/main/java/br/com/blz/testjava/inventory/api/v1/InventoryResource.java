package br.com.blz.testjava.inventory.api.v1;

import br.com.blz.testjava.inventory.Inventory;
import br.com.blz.testjava.inventory.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/inventories")
public class InventoryResource {

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
        return service.alterInventory(inventory);
    }
}
