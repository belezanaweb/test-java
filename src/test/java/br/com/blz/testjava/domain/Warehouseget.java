package br.com.blz.testjava.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.blz.testjava.Mother.WarehouseMother;

@RunWith(MockitoJUnitRunner.class)
public class Warehouseget {

	@Test
	public void WarehouseDomainTest() {

		final WarehouseMother mother = new WarehouseMother();
		final Warehouse domain = mother.getWarehouse();
		Assert.assertEquals("Os valores devem ser iguais", domain, domain);

	}

}
