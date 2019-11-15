package br.com.blz.testjava.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.blz.testjava.Mother.InventoryMother;

@RunWith(MockitoJUnitRunner.class)
public class InventoryTest {

	@Test
	public void InventoryDomainTest() {

		final InventoryMother mother = new InventoryMother();
		final Inventory domain = mother.getInventory();
		Assert.assertEquals("Os valores devem ser iguais", domain, domain);

	}

}
