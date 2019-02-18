package br.com.blz.testjava.resoucers;

import br.com.blz.testjava.models.Warehouse;
import br.com.blz.testjava.respositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WarehouseController {

    @Autowired
    private WarehouseRepository wr;

    @PostMapping("warehouse")
    public Warehouse postWarehouse(@RequestBody Warehouse wh){
        return wr.save(wh);
    }
}
