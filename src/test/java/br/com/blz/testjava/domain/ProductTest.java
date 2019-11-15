package br.com.blz.testjava.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.blz.testjava.Mother.ProductMother;

@RunWith(MockitoJUnitRunner.class)
public class ProductTest {

	@Test
	public void ProductTest() {
		final ProductMother mother = new ProductMother();
		final Warehouse ret = mother.getWarehouse();
		final Inventory domain = mother.getInventory();
		Assert.assertEquals("Os valores devem ser iguais", ret, ret);
		Assert.assertEquals("Os valores devem ser iguais", domain, domain);

	}
}