package br.com.blz.testjava.resoucers;

import br.com.blz.testjava.models.Inventory;
import br.com.blz.testjava.respositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InventoryController {
    @Autowired
    private InventoryRepository ir;
    
    @PostMapping("inventory")
    public Inventory save(@RequestBody Inventory inventory){
        return ir.save(inventory);
    }
}
