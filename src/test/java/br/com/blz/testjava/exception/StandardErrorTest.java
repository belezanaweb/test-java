package br.com.blz.testjava.exception;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StandardErrorTest {

	@Test
	public void testStandardError() {

		final StandardErrorTest mother = new StandardErrorTest();

		final StandardErrorTest response = new StandardErrorTest();
		Assert.assertEquals("Os valores devem ser iguais", response, response);

	}
}






