package br.com.blz.testjava.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static br.com.blz.testjava.TestUtils.getProduct;
import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void quantityTotalIsZeroReturnIsMarketableIsFalse() {
        var dto = getProduct(1L, "Loreal", 2, -2);
        assertThat(dto.isMarketable()).isFalse();
        assertThat(dto.getInventory().getQuantity()).isEqualTo(0);
    }

    @Test
    void quantityTotalLeZeroReturnIsMarketableIsFalse() {
        var dto = getProduct(1L, "Loreal", -1, -5);
        assertThat(dto.isMarketable()).isFalse();
        assertThat(dto.getInventory().getQuantity()).isEqualTo(-6);
    }

    @Test
    void quantityTotalGeZeroReturnIsMarketableIsTrue() {
        var dto = getProduct(1L, "Loreal", 12, 3);
        assertThat(dto.isMarketable()).isTrue();
        assertThat(dto.getInventory().getQuantity()).isEqualTo(15);
    }

    @Test
    void inventoryNullReturnIsMarketableFalse() {
        assertThat(new Product().isMarketable()).isFalse();
    }

    @Test
    void wareHousesNullOrEmptyReturnsQuantityZero() {
        var product = getProduct(1L, "Loreal", 12, 3);
        product.getInventory().setWarehouses(null);
        assertThat(product.isMarketable()).isFalse();

        product.getInventory().setWarehouses(Collections.emptyList());
        assertThat(product.isMarketable()).isFalse();
    }

}
