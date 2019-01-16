package br.com.blz.testjava.model;

import static br.com.blz.testjava.utils.ObjectCreator.getInventory;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class InventoryTest {
	@Test
	public void testEqualsAndHashCodeSymmetric() {
		Inventory inventory1 = getInventory();
		Inventory inventory2 = getInventory();
		
		assertThat(inventory1.equals(inventory2)).isTrue();
		assertThat(inventory1.hashCode()).isEqualTo(inventory2.hashCode());
		assertThat(inventory1.toString()).isEqualTo(inventory2.toString());
	}
}