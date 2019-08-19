package br.com.blz.testjava.util;

import br.com.blz.testjava.enums.Type;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.Produto;
import br.com.blz.testjava.model.Warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {

    static Map<Integer, Produto> produtosCreated = new HashMap<>();

    public static void createData() {

        Warehouse w1 = new Warehouse("SP", 12, Type.ECOMMERCE);
        Warehouse w2 = new Warehouse("SP", 3, Type.PHYSICAL_STORE);

        List<Warehouse> l1 = new ArrayList<>();
        l1.add(w1);
        l1.add(w2);

        Inventory i1 = new Inventory(l1);
        Produto p1 = new Produto(43264, "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de econstrução500g", i1);

        Produto p2 = new Produto(96529, "L'Oréal Professionnel Expert Vitamino Color A-OX - Máscara Capilar 500ml", i1);

        Produto p3 = new Produto(23601, "Vult Make Up Efeito Matte 04 Bege - Base Líquida 26ml", i1);

        Produto p4 = new Produto(75641, "Vult Carbon Black - Caneta Delineadora 1,6g", i1);

        Produto p5 = new Produto(85604, "Vult Carbon Black - Caneta Delineadora 1,6g", i1);

        produtosCreated.put(p1.getSku(), p1);
        produtosCreated.put(p2.getSku(), p2);
        produtosCreated.put(p3.getSku(), p3);
        produtosCreated.put(p4.getSku(), p4);
        produtosCreated.put(p5.getSku(), p5);

    }

    public static void saveNewProduto(Produto p){

        produtosCreated.put(p.getSku(), p);

    }

    public static Produto get(int sku){

        return produtosCreated.get(sku);

    }

    public static Produto delete(int sku){

        return produtosCreated.remove(sku);

    }

}
