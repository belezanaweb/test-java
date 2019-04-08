package br.com.blz.testjava.usecases.impl;

import static br.com.blz.testjava.usecases.impl.ProductCreateImpl.INTERNAL_SERVER_ERROR;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import br.com.blz.testjava.domains.Inventory;
import br.com.blz.testjava.domains.Product;
import br.com.blz.testjava.domains.Warehouse;
import br.com.blz.testjava.domains.enums.TypeInventory;
import br.com.blz.testjava.exceptions.InternalServerErrorException;
import br.com.blz.testjava.exceptions.NotFoundProductException;
import br.com.blz.testjava.gateways.ProductGateway;
import br.com.blz.testjava.gateways.http.converter.ProductConverter;
import br.com.blz.testjava.gateways.http.converter.ProductResponseJSON;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductDeleteImplTest {

    private static final String NOT_FOUND = "Not found element by sku: ";

    @InjectMocks
    private ProductDeleteImpl usecase;

    @Mock
    private ProductConverter converter;

    @Mock
    private ProductGateway gateway;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void executeDeleteOk() {
        // GIVEN
        Inventory inventory = getInventory();
        Product productBase = getProduct(inventory);
        ProductResponseJSON responseJSON = getProductResponseJSON(inventory);

        when(this.converter.convertDomainToResponse(anyObject()))
            .thenReturn(responseJSON);

        when(this.gateway.findById(anyInt()))
            .thenReturn(productBase);

        // WHEN
        this.usecase.execute(123);
    }

    @Test
    public void executeDeleteNull() {
        // GIVEN
        Inventory inventory = getInventory();
        Product product = getProduct(inventory);
        ProductResponseJSON responseJSON = getProductResponseJSON(inventory);

        when(this.converter.convertDomainToResponse(product))
            .thenReturn(responseJSON);

        when(this.gateway.findById(anyInt()))
            .thenThrow(new InternalServerErrorException(INTERNAL_SERVER_ERROR));

        exception.expect(InternalServerErrorException.class);
        exception.expectMessage(INTERNAL_SERVER_ERROR);

        // WHEN
        this.usecase.execute(123);
    }

    @Test
    public void executeDeleteInternalServerError() {
        // GIVEN
        Inventory inventory = getInventory();
        Product product = getProduct(inventory);
        ProductResponseJSON responseJSON = getProductResponseJSON(inventory);

        when(this.converter.convertDomainToResponse(product))
            .thenReturn(responseJSON);

        when(this.gateway.findById(anyInt()))
            .thenThrow(new NotFoundProductException(NOT_FOUND));

        exception.expect(NotFoundProductException.class);
        exception.expectMessage(NOT_FOUND);

        // WHEN
        this.usecase.execute(123);
    }

    private ProductResponseJSON getProductResponseJSON(Inventory inventory) {
        return ProductResponseJSON
            .builder()
            .sku(1234)
            .name("Teste1")
            .marketable(true)
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
            .warehouses(getWarehouses())
            .build();
    }

    private List<Warehouse> getWarehouses() {
        List<Warehouse> warehouses = new ArrayList<>();
        warehouses.add(Warehouse
            .builder()
            .locality("CE")
            .quantity(20)
            .type(TypeInventory.PHYSICAL_STORE)
            .build());
        warehouses.add(Warehouse
            .builder()
            .locality("AM")
            .quantity(152)
            .type(TypeInventory.PHYSICAL_STORE)
            .build());
        return warehouses;
    }
}
