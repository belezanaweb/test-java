package br.com.blz.testjava.usecases.impl;

import static br.com.blz.testjava.usecases.impl.ProductCreateImpl.INTERNAL_SERVER_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import br.com.blz.testjava.domains.Inventory;
import br.com.blz.testjava.domains.Product;
import br.com.blz.testjava.exceptions.InternalServerErrorException;
import br.com.blz.testjava.gateways.ProductGateway;
import br.com.blz.testjava.gateways.http.converter.ProductConverter;
import br.com.blz.testjava.gateways.http.converter.ProductResponseJSON;
import java.util.ArrayList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductUpdateImplTest {

    @InjectMocks
    private ProductUpdateImpl usecase;

    @Mock
    private ProductConverter converter;

    @Mock
    private ProductGateway gateway;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void executeUpdateOk() {
        // GIVEN
        Inventory inventory = getInventory();
        Product productUpdate = getProduct(inventory);
        Product productBase = getProduct(inventory);
        ProductResponseJSON responseJSON = getProductResponseJSON(inventory);

        when(this.converter.convertDomainToResponse(productUpdate))
            .thenReturn(responseJSON);

        when(this.gateway.findById(anyInt()))
            .thenReturn(productBase);

        when(this.gateway.save(productUpdate))
            .thenReturn(productUpdate);

        // WHEN
        ProductResponseJSON response = this.usecase.execute(123, productUpdate);

        // THEN
        assertThat(responseJSON).isEqualTo(response);
    }

    @Test
    public void executeUpdateNull() {
        // GIVEN
        Inventory inventory = getInventory();
        Product product = getProduct(inventory);
        ProductResponseJSON responseJSON = getProductResponseJSON(inventory);

        when(this.converter.convertDomainToResponse(product))
            .thenReturn(responseJSON);

        exception.expect(InternalServerErrorException.class);
        exception.expectMessage(INTERNAL_SERVER_ERROR);

        // WHEN
        ProductResponseJSON response = this.usecase.execute(123, product);
    }

    private ProductResponseJSON getProductResponseJSON(Inventory inventory) {
        return ProductResponseJSON
            .builder()
            .sku(1234)
            .name("Teste1")
            .inventory(inventory)
            .build();
    }

    private Product getProduct(Inventory inventory) {
        return Product
            .builder()
            .sku(1234)
            .name("Teste1")
            .inventory(inventory)
            .build();
    }

    private Inventory getInventory() {
        return Inventory
            .builder()
            .quantity(1)
            .warehouses(new ArrayList<>())
            .build();
    }
}
