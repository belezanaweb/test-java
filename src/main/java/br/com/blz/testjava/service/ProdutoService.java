package br.com.blz.testjava.service;

import br.com.blz.testjava.Repository.WarehouseRepository;
import br.com.blz.testjava.entity.Produto;
import br.com.blz.testjava.entity.Warehouse;
import br.com.blz.testjava.entity.dto.ProdutoEntradaDTO;
import br.com.blz.testjava.entity.dto.ProdutoRetornoDTO;
import br.com.blz.testjava.entity.dto.WarehouseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class ProdutoService {

    @Autowired
    WarehouseRepository repository;

    Logger logger = LoggerFactory.getLogger(ProdutoService.class);

    public void save(ProdutoEntradaDTO dto){

        List<WarehouseDTO> warehousesDTO = dto.getInventory().getWarehouses();

        for (WarehouseDTO item : warehousesDTO) {
            Warehouse warehouse = repository.findWarehouseByUf(item.getLocality());
            Produto produto = new Produto(dto.getSku(), dto.getName(), item.getQuantity());
            warehouse.saveProduto(produto);
            repository.save(warehouse);
        }
        logger.info(String.format("Produto salvo com sucesso! SKU %d", dto.getSku()));
    }

    public ProdutoRetornoDTO getProduto(Long sku){

        ProdutoRetornoDTO produtoRetornoDTO = new ProdutoRetornoDTO();
        produtoRetornoDTO.setSku(sku);
        int quantidade = 0;
        Set<Warehouse> warehouses = repository.list();

        for (Warehouse warehouse: warehouses) {
            if(warehouse.existeNoEstoque(sku)){
                Produto produtoExistente = warehouse.findProdutoBySku(sku);
                if(produtoRetornoDTO.getName() == null){
                    produtoRetornoDTO.setName(produtoExistente.getName());
                }
                WarehouseDTO warehouseDTO = new WarehouseDTO(warehouse.getLocality(), warehouse.getType(), produtoExistente.getQuantity());
                quantidade += produtoExistente.getQuantity();
                produtoRetornoDTO.getInventory().getWarehouses().add(warehouseDTO);
            }
        }

        produtoRetornoDTO.getInventory().setQuantity(quantidade);
        produtoRetornoDTO.setIsMarketable(produtoRetornoDTO.getInventory().getQuantity() >= 0);

        if (produtoRetornoDTO.getInventory().getWarehouses().size() == 0){
            logger.error(String.format("Produto não encontrado! SKU %d", sku));
            throw new NoSuchElementException();
        }

        produtoRetornoDTO.setIsMarketable(produtoRetornoDTO.getInventory().getQuantity() > 0);

        logger.info(String.format("Produto encontrado! SKU %d", sku));

        return produtoRetornoDTO;

    }

    public ProdutoRetornoDTO atualizar(Long sku, ProdutoEntradaDTO dto){

        List<WarehouseDTO> warehousesDTO = dto.getInventory().getWarehouses();

        for (WarehouseDTO item : warehousesDTO) {
            Warehouse warehouse = repository.findWarehouseByUf(item.getLocality());
            Produto produto = new Produto(sku, dto.getName(), item.getQuantity());
            warehouse.atualizarProduto(produto);
            repository.save(warehouse);
        }

        logger.info(String.format("Produto atualizado com sucesso! SKU %d", sku));
        return getProduto(sku);
    }

    public void deletar(Long sku){

        for (Warehouse warehouse : repository.list()) {
            warehouse.deletarProduto(sku);
            repository.save(warehouse);
        }
        logger.info(String.format("Produto deletado com sucesso! SKU %d", sku));
    }
}
