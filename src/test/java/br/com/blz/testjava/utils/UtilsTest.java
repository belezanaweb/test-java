package br.com.blz.testjava.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.MockModels;
import br.com.blz.testjava.model.SKU;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsTest {
	
	Utils util = new Utils();
	MockModels mockModels = new MockModels();
		
	@Test
	public void testUtils() throws Exception {
		SKU sku = mockModels.mockSKU1();
		Assert.assertNotNull(sku);
		
		util.countQuantity(sku);
		Assert.assertEquals(new Integer(5), sku.getInventory().getQuantity());
		
		util.checkIsMarketable(sku);
		Assert.assertTrue(sku.getIsMarketable());
	}
	
}
