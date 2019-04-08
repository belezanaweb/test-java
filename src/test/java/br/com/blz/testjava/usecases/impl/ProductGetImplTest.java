package br.com.blz.testjava.usecases.impl;

import static br.com.blz.testjava.usecases.impl.ProductCreateImpl.INTERNAL_SERVER_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import br.com.blz.testjava.domains.Inventory;
import br.com.blz.testjava.domains.Product;
import br.com.blz.testjava.domains.Warehouse;
import br.com.blz.testjava.domains.enums.TypeInventory;
import br.com.blz.testjava.exceptions.InternalServerErrorException;
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
public class ProductGetImplTest {

    @InjectMocks
    private ProductGetImpl usecase;

    @Mock
    private ProductConverter converter;

    @Mock
    private ProductGateway gateway;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void executeGetOk() {
        // GIVEN
        Inventory inventory = getInventory();
        Product productBase = getProduct(inventory);
        ProductResponseJSON responseJSON = getProductResponseJSON(inventory);

        when(this.converter.convertDomainToResponse(anyObject()))
            .thenReturn(responseJSON);

        when(this.gateway.findById(anyInt()))
            .thenReturn(productBase);

        // WHEN
        ProductResponseJSON response = this.usecase.execute(123);

        // THEN
        assertThat(responseJSON.getInventory().getWarehouses().size()).isEqualTo(2);
        assertThat(responseJSON.getInventory().getQuantity()).isEqualTo(172);
        assertThat(responseJSON.getMarketable()).isEqualTo(Boolean.TRUE);
    }

    @Test
    public void executeGetNull() {
        // GIVEN
        Inventory inventory = getInventory();
        Product product = getProduct(inventory);
        ProductResponseJSON responseJSON = getProductResponseJSON(inventory);

        when(this.converter.convertDomainToResponse(product))
            .thenReturn(responseJSON);

        exception.expect(InternalServerErrorException.class);
        exception.expectMessage(INTERNAL_SERVER_ERROR);

        // WHEN
        ProductResponseJSON response = this.usecase.execute(123);
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
