package br.com.blz.testjava.controller;

import br.com.blz.testjava.controller.dto.ProductDTO;
import br.com.blz.testjava.database.BelezaNaWebDatabase;
import br.com.blz.testjava.exception.ResourceAlreadyExistsException;
import br.com.blz.testjava.exception.ResourceNotFoundException;
import br.com.blz.testjava.model.WarehouseType;
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
public class UpdateProductTest {
    @Autowired private MockService mockService;
    @Autowired private ProductsController controller;

    @Before
    public void before() {
        BelezaNaWebDatabase.clear();
    }

    @Test
    public void updatingProductName() throws ResourceAlreadyExistsException, ResourceNotFoundException {
        final long sku = 200l;

        final ProductDTO original = mockService.createProduct(sku);
        final ProductDTO modified = mockService.createProduct(sku);
        modified.setName("New name");

        final ResponseEntity<ProductDTO> saveResponse = controller.save(
            original, UriComponentsBuilder.fromUriString(""));

        final ResponseEntity<ProductDTO> updateResponse = controller.update(sku, modified);

        assertSame(updateResponse.getStatusCode(), HttpStatus.OK);
        assertSame(modified.getName(), updateResponse.getBody().getName());
        assertNotEquals(saveResponse.getBody().getName(), updateResponse.getBody().getName());
    }

    @Test
    public void updatingWarehouse() throws ResourceAlreadyExistsException, ResourceNotFoundException {
        final long sku = 110l;

        final ProductDTO original = mockService.createProduct(sku);
        final ProductDTO modified = mockService.createProduct(sku);
        modified.getInventory().add(mockService.createWarehouse(WarehouseType.PHYSICAL_STORE, 10, "MG"));

        final ResponseEntity<ProductDTO> saveResponse = controller.save(
            original, UriComponentsBuilder.fromUriString(""));

        final ResponseEntity<ProductDTO> updateResponse = controller.update(sku, modified);
        final ProductDTO bodyResponse = updateResponse.getBody();

        assertSame(updateResponse.getStatusCode(), HttpStatus.OK);
        assertSame(bodyResponse.getInventory().getWarehouses().size(), modified.getInventory().getWarehouses().size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void tryingToUpdateAnInexistingProduct() throws ResourceNotFoundException {
        final ProductDTO product = mockService.createProduct();
        controller.update(product.getSku(), product);
    }
}
