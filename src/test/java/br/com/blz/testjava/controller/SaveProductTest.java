package br.com.blz.testjava.controller;

import br.com.blz.testjava.controller.ProductsController;
import br.com.blz.testjava.controller.dto.ProductDTO;
import br.com.blz.testjava.database.BelezaNaWebDatabase;
import br.com.blz.testjava.exception.ResourceAlreadyExistsException;
import br.com.blz.testjava.service.MockService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaveProductTest {
    @Autowired private MockService mockService;
    @Autowired private ProductsController controller;

    @Before
    public void before() {
        BelezaNaWebDatabase.clear();
    }

    @Test
    public void saveSuccessfull() throws ResourceAlreadyExistsException {
        final ProductDTO mockedProduct = mockService.createProduct();
        final ResponseEntity<ProductDTO> response = controller.save(
            mockedProduct, UriComponentsBuilder.fromUriString(""));

        final ProductDTO savedProduct = response.getBody();

        assertSame(response.getStatusCode(), HttpStatus.CREATED);
        assertSame(mockedProduct.getSku(), savedProduct.getSku());
        assertSame(mockedProduct.getName(), savedProduct.getName());
        assertSame(
            mockedProduct.getInventory().getWarehouses().size(),
            savedProduct.getInventory().getWarehouses().size());
    }

    @Test
    public void savingTwoDifferentsProductsInRow() throws ResourceAlreadyExistsException {
        final ProductDTO firstMock = mockService.createProduct(100l);
        final ProductDTO secondMock = mockService.createProduct(101l);

        final ResponseEntity<ProductDTO> firstResponse = controller.save(
            firstMock, UriComponentsBuilder.fromUriString(""));

        final ResponseEntity<ProductDTO> secondResponse = controller.save(
            secondMock, UriComponentsBuilder.fromUriString(""));

        final ProductDTO firstSaved = firstResponse.getBody();
        final ProductDTO secondSaved = secondResponse.getBody();

        assertSame(firstResponse.getStatusCode(), HttpStatus.CREATED);
        assertSame(secondResponse.getStatusCode(), HttpStatus.CREATED);

        assertSame(firstMock.getSku(), firstSaved.getSku());
        assertSame(secondMock.getSku(), secondSaved.getSku());
        assertNotEquals(firstSaved.getSku(), secondSaved.getSku());
    }

    @Test(expected = ResourceAlreadyExistsException.class)
    public void tryingToSaveAnExistingProduct() throws ResourceAlreadyExistsException {
        final ProductDTO mockedProduct = mockService.createProduct();

        final ResponseEntity<ProductDTO> firstResponse = controller.save(
            mockedProduct, UriComponentsBuilder.fromUriString(""));


        final ResponseEntity<ProductDTO> secondResponse = controller.save(
            mockedProduct, UriComponentsBuilder.fromUriString(""));
    }
}
