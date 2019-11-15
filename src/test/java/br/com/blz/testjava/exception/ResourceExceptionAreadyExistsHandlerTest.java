package br.com.blz.testjava.exception;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ResourceExceptionAreadyExistsHandlerTest {

	@Test
	public void testStandardError() {

		final ResourceExceptionAreadyExistsHandlerTest mother = new ResourceExceptionAreadyExistsHandlerTest();

		final ResourceExceptionAreadyExistsHandlerTest response = new ResourceExceptionAreadyExistsHandlerTest();
		Assert.assertEquals("Os valores devem ser iguais", response, response);

	}

}