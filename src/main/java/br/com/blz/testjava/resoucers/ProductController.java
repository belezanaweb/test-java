package br.com.blz.testjava.resoucers;

import br.com.blz.testjava.models.Product;
import br.com.blz.testjava.models.Warehouse;
import br.com.blz.testjava.respositories.InventoryRepository;
import br.com.blz.testjava.respositories.ProductRepository;
import br.com.blz.testjava.respositories.WarehouseRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
@Api(value = "REST API Products")
public class ProductController {

    @Autowired
    private ProductRepository pr;

    @Autowired
    private InventoryRepository ir;

    @Autowired
    private WarehouseRepository hr;

    @ApiOperation(value = "Return list of products")
    @GetMapping("/products")
    public List<Product> getProducts() {
        return pr.findAll();
    }

    @ApiOperation(value = "Return a specific product")
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable(value = "id") long sku) {
        Product product = pr.findBySku(sku);
        product.getInventory().setQuantity();
        product.marketable();
        System.out.println("\n\n\n\n " + product + "\n\n\n\n\n");
        return product;
    }

    @ApiOperation(value = "Persist a product and return them")
    @PostMapping("/product")
    public Product saveProduct(@RequestBody Product p) {
        for (Warehouse wh : p.getInventory().getWarehouses()) {
            hr.save(wh);
        }
        ir.save(p.getInventory());
        return pr.save(p);
    }

    @ApiOperation(value = "Delete a product")
    @DeleteMapping("/product")
    public void delet(@RequestBody long sku) {
        Product product = pr.findBySku(sku);
        pr.delete(product);
    }

    @ApiOperation(value = "Update a product")
    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product p) {
        for (Warehouse wh : p.getInventory().getWarehouses()) {
            hr.save(wh);
        }
        ir.save(p.getInventory());
        return pr.save(p);
    }

}
