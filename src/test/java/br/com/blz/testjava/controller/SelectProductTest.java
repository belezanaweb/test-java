package br.com.blz.testjava.controller;

import br.com.blz.testjava.controller.dto.ProductDTO;
import br.com.blz.testjava.database.BelezaNaWebDatabase;
import br.com.blz.testjava.exception.ResourceAlreadyExistsException;
import br.com.blz.testjava.exception.ResourceNotFoundException;
import br.com.blz.testjava.service.MockService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.Assert.assertSame;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SelectProductTest {
    @Autowired private MockService mockService;
    @Autowired private ProductsController controller;

    @Before
    public void before() {
        BelezaNaWebDatabase.clear();
    }

    @Test
    public void selectingExistingProduct() throws ResourceAlreadyExistsException, ResourceNotFoundException {
        final Long sku = 100l;
        final ProductDTO product = mockService.createProduct(sku);

        controller.save(product, UriComponentsBuilder.fromUriString(""));
        final ResponseEntity<ProductDTO> response = controller.findBy(sku);

        assertSame(response.getStatusCode(), HttpStatus.OK);
        assertSame(response.getBody().getSku(), product.getSku());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void selectingAnInvalidProduct() throws ResourceNotFoundException {
        final Long sku = 100l;
        controller.findBy(sku);
    }
}
