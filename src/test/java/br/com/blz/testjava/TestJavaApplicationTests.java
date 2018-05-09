package br.com.blz.testjava;

import br.com.blz.testjava.model.DefaultMessage;
import br.com.blz.testjava.model.Inventory;
import br.com.blz.testjava.model.SkuProduct;
import br.com.blz.testjava.model.Warehouse;
import br.com.blz.testjava.service.SkuProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJavaApplicationTests {

    private SkuProductService skuProductService;
    private SkuProduct skuProduct;

	@Before
    public void initialize() {
        skuProductService = new SkuProductService();

	    skuProduct = new SkuProduct();
        skuProduct.setSku(43264);
        skuProduct.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
        skuProduct.setInventory(new Inventory());
        List<Warehouse> warehouses = new ArrayList<>();
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setLocality("SP");
        warehouse1.setQuantity(12);
        warehouse1.setType("ECOMMERCE");
        warehouses.add(warehouse1);
        Warehouse warehouse2 = new Warehouse();
        warehouse2.setLocality("MOEMA");
        warehouse2.setQuantity(3);
        warehouse2.setType("PHYSICAL_STORE");
        warehouses.add(warehouse2);
        skuProduct.getInventory().setWarehouses(warehouses);
    }

	@Test
	public void testCreate() {
        ResponseEntity<DefaultMessage> defaultMessageResponseEntity =  skuProductService.create(skuProduct);
        Assert.assertEquals(HttpStatus.CREATED, defaultMessageResponseEntity.getStatusCode());
	}

    @Test
    public void testCreateTwice() {
        ResponseEntity<DefaultMessage> defaultMessageResponseEntity =  skuProductService.create(skuProduct);
        defaultMessageResponseEntity =  skuProductService.create(skuProduct);
        Assert.assertEquals(HttpStatus.CONFLICT, defaultMessageResponseEntity.getStatusCode());
    }

    @Test
    public void testGet() {
        skuProductService.create(skuProduct);
        ResponseEntity<SkuProduct> skuProductResponseEntity =  skuProductService.getSkuProduct(skuProduct.getSku());
        Assert.assertEquals(HttpStatus.OK, skuProductResponseEntity.getStatusCode());
        Assert.assertTrue(skuProductResponseEntity.getBody().isMarketable());
        Assert.assertTrue(skuProductResponseEntity.getBody().getInventory().getQuantity() > 0);
    }

    @Test
    public void testGetQuantityZero() {
        skuProductService.create(skuProduct);
        skuProduct.getInventory().getWarehouses().get(0).setQuantity(0);
        skuProduct.getInventory().getWarehouses().get(1).setQuantity(0);
        skuProductService.updateProduct(skuProduct);
        ResponseEntity<SkuProduct> skuProductResponseEntity =  skuProductService.getSkuProduct(skuProduct.getSku());
        Assert.assertEquals(HttpStatus.OK, skuProductResponseEntity.getStatusCode());
        Assert.assertTrue(!skuProductResponseEntity.getBody().isMarketable());
        Assert.assertTrue(skuProductResponseEntity.getBody().getInventory().getQuantity() == 0);
    }

    @Test
    public void testGetNoContent() {
        ResponseEntity<SkuProduct> skuProductResponseEntity =  skuProductService.getSkuProduct(1234);
        Assert.assertEquals(HttpStatus.NO_CONTENT, skuProductResponseEntity.getStatusCode());
    }

    @Test
    public void testUpdate() {
        skuProductService.create(skuProduct);
	    skuProduct.getInventory().getWarehouses().get(0).setQuantity(0);
        ResponseEntity<DefaultMessage> defaultMessageResponseEntity =  skuProductService.updateProduct(skuProduct);
        Assert.assertEquals(HttpStatus.OK, defaultMessageResponseEntity.getStatusCode());
        Assert.assertTrue(skuProduct.getInventory().getWarehouses().get(0).getQuantity() == 0);
    }

    @Test
    public void testUpdateNoContent() {
        ResponseEntity<DefaultMessage> defaultMessageResponseEntity =  skuProductService.updateProduct(skuProduct);
        Assert.assertEquals(HttpStatus.NO_CONTENT, defaultMessageResponseEntity.getStatusCode());
    }

    @Test
    public void testDelete() {
        skuProductService.create(skuProduct);
        ResponseEntity<DefaultMessage> defaultMessageResponseEntity =  skuProductService.delete(skuProduct.getSku());
        Assert.assertEquals(HttpStatus.OK, defaultMessageResponseEntity.getStatusCode());
    }

    @Test
    public void testDeleteNoContent() {
        ResponseEntity<DefaultMessage> defaultMessageResponseEntity =  skuProductService.delete(1234);
        Assert.assertEquals(HttpStatus.NO_CONTENT, defaultMessageResponseEntity.getStatusCode());
    }

}
