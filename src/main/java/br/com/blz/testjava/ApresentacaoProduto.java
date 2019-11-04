package br.com.blz.testjava;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.model.Warehouses;
import br.com.blz.testjava.service.ProdutoService;

@Component
public class ApresentacaoProduto implements ApplicationRunner {
    private ProdutoService service;

    @Autowired
    public ApresentacaoProduto(ProdutoService service) {
        this.service = service;
    }

    public void run(ApplicationArguments args) {
        this.service.salvar(ApresentacaoProduto.criarProduto());
    }

    public static Produto criarProduto() {
        Produto produto = new Produto();
        produto.setSku(43264);
        produto.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
       //criacao das Warehouses 1
        List<Warehouses> armazenamentos = new ArrayList<>();
        Warehouses armazenamento = new Warehouses();
        armazenamento.setLocality("SP");
        armazenamento.setQuantity(12);
        armazenamento.setType("ECOMMERCE");
        //Add o objeto 1
        armazenamentos.add(armazenamento);
        //criacao das Warehouses 2
        Warehouses objArmazenamento = new Warehouses();
        objArmazenamento.setLocality("MOEMA");
        objArmazenamento.setQuantity(3);
        objArmazenamento.setType("PHYSICAL_STORE");
        //Add o objeto 2
        armazenamentos.add(objArmazenamento);
        Inventory inventory = new Inventory();
        inventory.setWarehouses(armazenamentos);
        produto.setInventory(inventory);
        //retorno do objeto
        return produto;
    }
}