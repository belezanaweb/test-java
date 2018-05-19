package br.com.blz.testjava;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;

import br.com.blz.controller.SkuController;
import br.com.blz.enums.WarehouseTypeEnum;
import br.com.blz.model.Inventory;
import br.com.blz.model.Sku;
import br.com.blz.model.Warehouse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {TestJavaApplication.class})
@PropertySource(value= {"classpath:application.yml","classpath:messages.properties"})
@WebMvcTest(SkuController.class)
public class SkuControllerTest extends AbstractControllerTest {
	
	@Test
	public void adicionarNovoSku() throws Exception {
		this.forwardAddNewSku();
	}
	
	@Test(expected=Exception.class)
	public void duplicarSku() throws Exception{
		Sku newSku = forwardAddNewSku();
		this.addSku(newSku).andExpect(status().isBadRequest());
	}
	
	@Test 
	public void obterSku() throws Exception{
		Sku newSku = forwardAddNewSku();
		this.getSku(newSku);
	}
	
	@Test
	public void obterSkuInexistente() throws Exception{
		Sku skuInexistente = getNewSku();
		skuInexistente.setSku(99L);
		this.getSku(skuInexistente).andExpect(status().isNotFound());
	}
	
	@Test
	public void adicionarSkuSemNome() throws Exception {
		Sku skuSemNome = getNewSku();
		skuSemNome.setName(null);
		this.addSku(skuSemNome).andExpect(status().isBadRequest());
	}
	
	@Test
	public void adicionarSkuSemId() throws Exception{
		Sku skuSemId = getNewSku();
		skuSemId.setSku(null);
		this.addSku(skuSemId).andExpect(status().isBadRequest());
	}
	
	@Test
	public void adicionarSkuComIdInvalido() throws Exception{
		Sku skuComIdInvalido = getNewSku();
		skuComIdInvalido.setSku(-100L);
		this.addSku(skuComIdInvalido).andExpect(status().isBadRequest());
	}
	
	@Test
	public void adicionarSkuSemInventario() throws Exception{
		Sku skuSemInventario = getNewSku();
		skuSemInventario.setInventory(null);
		this.addSku(skuSemInventario).andExpect(status().isBadRequest());
	}
	
	@Test
	public void adicionarSkuSemWarehouses() throws Exception{
		Sku skuSemWarehouse = getNewSku();
		skuSemWarehouse.getInventory().setWarehouses(null);
		this.addSku(skuSemWarehouse).andExpect(status().isBadRequest());
	}
	
	@Test
	public void adicionarSkuComLocalidadeWarehouseInvalido() throws Exception{
		Sku skuComWarehouseInvalido = getNewSku();
		for( Warehouse warehouse : skuComWarehouseInvalido.getInventory().getWarehouses() ) {
			warehouse.setLocality(null);
		}
		this.addSku(skuComWarehouseInvalido).andExpect(status().isBadRequest());
	}
	
	@Test
	public void adicionarSkuComTipoWarehouseInvalido() throws Exception{
		Sku skuComWarehouseInvalido = getNewSku();
		for( Warehouse warehouse : skuComWarehouseInvalido.getInventory().getWarehouses() ) {
			warehouse.setType(null);
		}
		this.addSku(skuComWarehouseInvalido).andExpect(status().isBadRequest());
	}

	@Test
	public void adicionarSkuComQuantidadeWarehouseInvalido() throws Exception{
		Sku skuComWarehouseInvalido = getNewSku();
		for( Warehouse warehouse : skuComWarehouseInvalido.getInventory().getWarehouses() ) {
			warehouse.setQuantity(null);
		}
		this.addSku(skuComWarehouseInvalido).andExpect(status().isBadRequest());
	}

	public Sku forwardAddNewSku() throws Exception {
		Sku sku = getNewSku();
		this.addSku(sku).andExpect(status().isOk());
		return sku;
	}
	
	public ResultActions addSku(Sku sku) throws Exception {
		return executePostRequest("/api/sku/create", sku);
	}
	
	public ResultActions getSku(Sku sku) throws Exception {
		return executeGetRequest("/api/sku/get/" + sku.getSku());
	}	
	
	public Sku getNewSku() {
		Warehouse warehouse1 = new Warehouse();
		warehouse1.setLocality("MOEMA");
		warehouse1.setQuantity(15);
		warehouse1.setType(WarehouseTypeEnum.ECOMMERCE);
		
		Warehouse warehouse2 = new Warehouse();
		warehouse2.setLocality("SANTANA");
		warehouse2.setQuantity(20);
		warehouse2.setType(WarehouseTypeEnum.PHYSICAL_STORE);
		
		Set<Warehouse> warehouses = new HashSet<>();
		warehouses.add(warehouse1);
		warehouses.add(warehouse2);
		
		Inventory inventory = new Inventory();
		inventory.setWarehouses(warehouses);
		
		Sku sku = new Sku();
		sku.setName("product description model " + ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE));
		sku.setSku(ThreadLocalRandom.current().nextLong(100, Long.MAX_VALUE));
		sku.setInventory(inventory);
		
		return sku;
	}

}
