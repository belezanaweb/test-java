package br.com.blz.testjava.exception;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ResourceExceptionHandlerTest {

	@Test
	public void testStandardError() {

		final ResourceExceptionHandler mother = new ResourceExceptionHandler();
		final ResourceExceptionHandlerTest response = new ResourceExceptionHandlerTest();
		Assert.assertEquals("Os valores devem ser iguais", mother, mother);
		Assert.assertEquals("Os valores devem ser iguais", response, response);

	}

}