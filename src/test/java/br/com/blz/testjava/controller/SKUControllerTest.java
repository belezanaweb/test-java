package br.com.blz.testjava.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.MockModels;
import br.com.blz.testjava.dao.SKUDAO;
import br.com.blz.testjava.enums.Types;
import br.com.blz.testjava.model.SKU;
import br.com.blz.testjava.service.SKUService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SKUControllerTest {
	
	@InjectMocks
	SKUService service;
	
	@Mock
	SKUDAO dao;
	
	SKUController controller;
	
	MockModels mockModels = new MockModels();
	
	@Before
	public void setup() throws Exception {
		controller = new SKUController(service);
		SKU retorno1 = mockModels.mockSKU1();
		SKU retorno2 = mockModels.mockSKU2();
		List<SKU> retornoList1 = new ArrayList<>();
		retornoList1.add(retorno1);
		retornoList1.add(retorno2);
		Mockito.when(dao.insert(Mockito.any(SKU.class))).thenReturn(Boolean.TRUE);
		Mockito.when(dao.update(Mockito.anyInt(), Mockito.any(SKU.class))).thenReturn(Boolean.TRUE);
		Mockito.when(dao.delete(Mockito.anyInt())).thenReturn(null);
		Mockito.when(dao.getAll()).thenReturn(retornoList1);
		Mockito.when(dao.get(Mockito.anyInt())).thenReturn(retorno1);
	}
		
	@Test
	public void testInsert() throws Exception {
		SKU sku = mockModels.mockSKU1();
		Assert.assertNotNull(sku);
		sku = controller.insert(sku);
		Assert.assertEquals(new Integer(123), sku.getSku());
		Assert.assertEquals("Produto 1", sku.getName());
		Assert.assertTrue(sku.getIsMarketable());
		Assert.assertNotNull(sku.getInventory());
		Assert.assertEquals(new Integer(5), sku.getInventory().getQuantity());
		Assert.assertNotNull(sku.getInventory().getWarehouses());
		Assert.assertEquals(1, sku.getInventory().getWarehouses().size());
		Assert.assertEquals("CURITIBA", sku.getInventory().getWarehouses().get(0).getLocality());
		Assert.assertEquals(new Integer(5), sku.getInventory().getWarehouses().get(0).getQuantity());
		Assert.assertEquals(Types.PHYSICAL_STORE, sku.getInventory().getWarehouses().get(0).getType());
	}
	
	@Test
	public void testInsert2() throws Exception {
		SKU sku = mockModels.mockSKU2();
		Assert.assertNotNull(sku);
		sku = controller.insert(sku);
		Assert.assertEquals(new Integer(456), sku.getSku());
		Assert.assertEquals("Produto 2", sku.getName());
		Assert.assertTrue(sku.getIsMarketable());
		Assert.assertNotNull(sku.getInventory());
		Assert.assertEquals(new Integer(47), sku.getInventory().getQuantity());
		Assert.assertNotNull(sku.getInventory().getWarehouses());
		Assert.assertEquals(2, sku.getInventory().getWarehouses().size());
		Assert.assertEquals("SP", sku.getInventory().getWarehouses().get(0).getLocality());
		Assert.assertEquals(new Integer(15), sku.getInventory().getWarehouses().get(0).getQuantity());
		Assert.assertEquals(Types.ECOMMERCE, sku.getInventory().getWarehouses().get(0).getType());
		Assert.assertEquals("RJ", sku.getInventory().getWarehouses().get(1).getLocality());
		Assert.assertEquals(new Integer(32), sku.getInventory().getWarehouses().get(1).getQuantity());
		Assert.assertEquals(Types.ECOMMERCE, sku.getInventory().getWarehouses().get(1).getType());
	}
	
	@Test
	public void testGetAll() throws Exception {
		List<SKU> skus = controller.getAll();
		Assert.assertNotNull(skus);
		Assert.assertEquals(2, skus.size());
		
		Assert.assertEquals(new Integer(123), skus.get(0).getSku());
		Assert.assertEquals(new Integer(456), skus.get(1).getSku());		
	}
	
	@Test
	public void testGet() throws Exception {
		Integer key = 123;
		SKU sku = controller.get(key);
		Assert.assertNotNull(sku);
		
		Assert.assertEquals(new Integer(123), sku.getSku());		
	}
	
	@Test
	public void testUpdate() throws Exception {
		Integer key = 123;
		SKU sku = mockModels.mockSKU1();
		sku.setName("PRODUTO ATUALIZADO");
		sku.getInventory().getWarehouses().get(0).setQuantity(49);
		sku = controller.update(key, sku);
		Assert.assertNotNull(sku);
		
		Assert.assertEquals(new Integer(123), sku.getSku());
		Assert.assertEquals("PRODUTO ATUALIZADO", sku.getName());
		Assert.assertTrue(sku.getIsMarketable());
		Assert.assertNotNull(sku.getInventory());
		Assert.assertEquals(new Integer(49), sku.getInventory().getQuantity());
	}
	
	@Test
	public void testDelete() throws Exception {
		Integer key = 123;
		controller.delete(key);
		SKU retorno2 = mockModels.mockSKU2();
		List<SKU> retornoList2 = new ArrayList<>();
		retornoList2.add(retorno2);
		Mockito.when(dao.getAll()).thenReturn(retornoList2);
		List<SKU> skus = controller.getAll();
		Assert.assertNotNull(skus);
		Assert.assertEquals(1, skus.size());
		Assert.assertEquals(new Integer(456), skus.get(0).getSku());
	}
	
}
