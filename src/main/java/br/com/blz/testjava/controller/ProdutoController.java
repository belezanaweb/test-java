package br.com.blz.testjava.controller;

import br.com.blz.testjava.dao.DAO;
import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.model.Response;
import br.com.blz.testjava.model.Warehouse;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProdutoController {

    @RequestMapping( "/teste" )
    public String teste(@RequestBody String teste) {
        return "It's working...! : " + teste;
    }

    @RequestMapping("/insertProduct")
    public Response insertProduct(@RequestBody Product product) {
        Response response = null;

        try {
            DAO.insertProduct(product);
            response = new Response(0, "Produto incluido com sucesso", null);
        } catch (Exception e){
            response = new Response(1, e.getMessage(), null);
        }

        return response;
    }

    @RequestMapping("/getProduct")
    public Response getProduct(@RequestBody Product product) {
        Response response = null;
        Product prod = null;

        try {
            prod = DAO.getProduct(product.getSku());
            prod.getInventory().setQuantity(0L);
            for (Warehouse wh : prod.getInventory().getWarehouses()){
                prod.getInventory().setQuantity(prod.getInventory().getQuantity() + wh.getQuantity());
            }

            prod.setIsMarketable((prod.getInventory().getQuantity() > 0));

            response = new Response(0, "Produto recuperado com sucesso", prod);
        } catch (Exception e){
            response = new Response(1, e.getMessage(), null);
        }

        return response;
    }
}
