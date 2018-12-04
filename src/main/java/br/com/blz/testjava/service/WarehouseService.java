/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.blz.testjava.service;

import br.com.blz.testjava.domain.Warehouse;
import br.com.blz.testjava.repository.WarehouseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository repository;

    public void save(@RequestBody Warehouse warehouse) {
        repository.save(warehouse);
    }

    public List<Warehouse> getByProductSku(@PathVariable Long sku) {
        return repository.findByProductSku(sku);
    }
    
    public void deleteByProductSku(Long sku){
        repository.deleteByProductSku(sku);
    }
}
