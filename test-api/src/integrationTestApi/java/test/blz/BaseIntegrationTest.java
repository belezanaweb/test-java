package test.blz;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import test.blz.bean.Inventory;
import test.blz.bean.ProductCreateRequest;
import test.blz.bean.ProductUpdateRequest;
import test.blz.bean.Warehouse;
import test.blz.bean.WarehouseType;
import test.blz.repository.ProductCache;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { ApiStarter.class,
        IntegrationTestConfiguration.class })
public class BaseIntegrationTest {

    @Autowired
    private ProductCache productCache;

    @LocalServerPort
    private int port;

    static final String CONTEXT_PATH = "/blz-api";

    static final String PATH = CONTEXT_PATH + "/product";

    @Before
    public void setUp () {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @After
    public void tearDown () {
        productCache.clearAll();
    }

    ProductCreateRequest buildCreateRequest(Long sku){

        List<Warehouse>warehouses = new ArrayList<>();
        warehouses.add(Warehouse.builder()
                .locality("sao paulo")
                .quantity(1)
                .type(WarehouseType.ECOMMERCE)
                .build());

        warehouses.add(Warehouse.builder()
                .locality("sao paulo")
                .quantity(1)
                .type(WarehouseType.PHYSICAL_STORE)
                .build());

        final Inventory inventory = Inventory.builder()
                .warehouses(warehouses)
                .build();

        return ProductCreateRequest.builder()
                .sku(sku)
                .name("produto teste")
                .inventory(inventory)
                .build();
    }

    ProductUpdateRequest buildUpdateRequest(){

        List<Warehouse>warehouses = new ArrayList<>();
        warehouses.add(Warehouse.builder()
                .locality("sao paulo")
                .quantity(1)
                .type(WarehouseType.ECOMMERCE)
                .build());

        warehouses.add(Warehouse.builder()
                .locality("sao paulo")
                .quantity(1)
                .type(WarehouseType.PHYSICAL_STORE)
                .build());

        final Inventory inventory = Inventory.builder()
                .warehouses(warehouses)
                .build();

        return ProductUpdateRequest.builder()
                .sku(1L)
                .name("produto teste")
                .inventory(inventory)
                .build();
    }

}
