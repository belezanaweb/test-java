package br.com.blz.testjava.Repository;

import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.service.ProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class WarehouseRepository {

    Logger logger = LoggerFactory.getLogger(ProdutoService.class);

    private final Set<Warehouse> warehouses = new HashSet<>();

    public void save(Warehouse warehouse) {
            this.warehouses.add(warehouse);
    }

    public Set<Warehouse> list() {
        return Collections.unmodifiableSet(this.warehouses);
    }

    public Warehouse findWarehouseByUf(String uf){
        for (Warehouse item : this.warehouses) {
            if (item.getLocality().equals(uf)){
                return item;
            }
        }
        logger.error("Warehouse não encontrada!");
        throw new NoSuchElementException();
    }

}
