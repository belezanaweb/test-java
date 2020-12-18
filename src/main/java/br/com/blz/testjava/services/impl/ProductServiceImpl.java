package br.com.blz.testjava.services.impl;

import br.com.blz.testjava.api.vos.ProductVO;
import br.com.blz.testjava.api.vos.WarehouseVO;
import br.com.blz.testjava.model.entities.Locality;
import br.com.blz.testjava.model.entities.Product;
import br.com.blz.testjava.model.entities.Warehouse;
import br.com.blz.testjava.model.exceptions.ProductUniqueKeyException;
import br.com.blz.testjava.model.repositories.LocalityRepository;
import br.com.blz.testjava.model.repositories.ProductRepository;
import br.com.blz.testjava.model.repositories.WarehouseRepository;
import br.com.blz.testjava.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final LocalityRepository localityRepository;
    private final WarehouseRepository warehouseRepository;

    private static String MSG_NOT_FOUND = "Produto não encontrado com sku %d";
    private static String MSG_LOCALITY_NOT_FOUND = "Localidade não encontrado com nome %s";
    private static String MSG_WAREHOUSE_NOT_FOUND = "Warehouse não encontrado";

    @Override
    public Product findBySku(Long sku) {
        return repository.findBySku(sku)
            .orElseThrow(() -> new EmptyResultDataAccessException(String.format(MSG_NOT_FOUND, sku), 0));
    }

    @Override
    @Transactional
    public Product save(ProductVO vo) {
        Product product = new Product();
        product.setSku(vo.getSku());
        product.setName(vo.getName());

        List<Warehouse> warehouses = new ArrayList<>();
        for (WarehouseVO w : vo.getInventory().getWarehouses()){

            Warehouse warehouse = Warehouse.builder()
                .product(product)
                .quantity(w.getQuantity())
                .type(w.getType())
                .locality(localityRepository.findByName(w.getLocality())
                    .orElseThrow(() -> new EmptyResultDataAccessException(String.format(MSG_LOCALITY_NOT_FOUND, w.getLocality()), 0)))
                .build();

            warehouses.add(warehouse);
        }

        product.setInventory(warehouses);

        try{
            return repository.save(product);
        }catch (DataIntegrityViolationException ex){
            throw new ProductUniqueKeyException(String.format("Produto com sku %d ja cadastrado!", product.getSku()));
        }
    }

    @Override
    @Transactional
    public boolean delete(Long sku) {
        Product product = repository.findBySku(sku)
            .orElseThrow(() -> new EmptyResultDataAccessException(String.format(MSG_NOT_FOUND, sku), 0));

        repository.delete(product);
        repository.flush();

        return true;
    }

    @Override
    @Transactional
    public Product update(ProductVO vo, Long sku) {
        Product product = repository.findBySku(sku)
            .orElseThrow(() -> new EmptyResultDataAccessException(String.format(MSG_NOT_FOUND, sku), 0));

        product.setSku(vo.getSku());
        product.setName(vo.getName());

        List<Warehouse> wareHouseRemove = new ArrayList<>();
        for(Warehouse pw : product.getInventory()){
            if(vo.getInventory().getWarehouses().stream()
                .filter(w -> w.getLocality().equals(pw.getLocality().getName())).count() == 0){
                wareHouseRemove.add(pw);
            }
        }

        for(Warehouse w : wareHouseRemove){
            product.getInventory().remove(w);
        }

        for (WarehouseVO w : vo.getInventory().getWarehouses()){
            Warehouse wareHouse = warehouseRepository.findByProductSkuAndNameLocality(sku, w.getLocality())
                .orElse(Warehouse.builder().product(product).build());

            Locality locality = localityRepository.findByName(w.getLocality())
                .orElseThrow(() -> new EmptyResultDataAccessException(String.format(MSG_LOCALITY_NOT_FOUND, w.getLocality()), 0));

            wareHouse.setLocality(locality);
            wareHouse.setQuantity(w.getQuantity());
            wareHouse.setType(w.getType());

            if(wareHouse.getId() != null){
                int idx = product.getInventory().indexOf(wareHouse);
                product.getInventory().remove(idx);
                product.getInventory().add(wareHouse);
            }else{
                product.getInventory().add(wareHouse);
            }
        }

        try{
            return repository.save(product);
        }catch (DataIntegrityViolationException ex){
            throw new ProductUniqueKeyException(String.format("Produto com sku %d ja cadastrado!", product.getSku()));
        }
    }
}
