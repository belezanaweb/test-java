package br.com.blz.testjava.usecases.impl;

import static br.com.blz.testjava.usecases.impl.ProductCreateImpl.INTERNAL_SERVER_ERROR;
import static br.com.blz.testjava.usecases.impl.ProductCreateImpl.MSG_SKUS_IGUAIS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import br.com.blz.testjava.domains.Inventory;
import br.com.blz.testjava.domains.Product;
import br.com.blz.testjava.exceptions.ConflictException;
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
public class ProductCreateImplTest {

    @InjectMocks
    private ProductCreateImpl usecase;

    @Mock
    private ProductConverter converter;

    @Mock
    private ProductGateway gateway;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void executeSaveOk() {
        // GIVEN
        Inventory inventory = getInventory();
        Product product = getProduct(inventory);
        ProductResponseJSON responseJSON = getProductResponseJSON(inventory);
        when(this.usecase.execute(product))
            .thenReturn(responseJSON);

        when(this.converter.convertDomainToResponse(product))
            .thenReturn(responseJSON);

        when(this.gateway.existsProductBySKU(product.getSku()))
            .thenReturn(false);

        when(this.gateway.save(product))
            .thenReturn(product);

        // WHEN
        ProductResponseJSON response = this.usecase.execute(product);

        // THEN
        assertThat(responseJSON).isEqualTo(response);
    }

    @Test
    public void executeSaveConflict() {
        // GIVEN
        Inventory inventory = getInventory();
        Product product = getProduct(inventory);
        ProductResponseJSON responseJSON = getProductResponseJSON(inventory);
        when(this.usecase.execute(product))
            .thenReturn(responseJSON);

        when(this.converter.convertDomainToResponse(product))
            .thenReturn(responseJSON);

        when(this.gateway.existsProductBySKU(product.getSku()))
            .thenReturn(true);

        exception.expect(ConflictException.class);
        exception.expectMessage(MSG_SKUS_IGUAIS);

        // WHEN
        ProductResponseJSON response = this.usecase.execute(product);
    }

    @Test
    public void executeSaveNull() {
        // GIVEN
        Inventory inventory = getInventory();
        Product product = getProduct(inventory);
        ProductResponseJSON responseJSON = getProductResponseJSON(inventory);
        when(this.usecase.execute(product))
            .thenReturn(responseJSON);

        when(this.converter.convertDomainToResponse(product))
            .thenReturn(responseJSON);

        when(this.gateway.existsProductBySKU(product.getSku()))
            .thenReturn(null);

        exception.expect(InternalServerErrorException.class);
        exception.expectMessage(INTERNAL_SERVER_ERROR);

        // WHEN
        ProductResponseJSON response = this.usecase.execute(product);
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
