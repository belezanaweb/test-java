package br.com.blz.testjava.component;

import br.com.blz.testjava.repository.ProductRepository;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class BaseTest {

    static final String HOST = "http://localhost:";

    static final String CREATE_JSON = "create.json";
    static final String REPLACE_JSON = "replace.json";
    static final String ERROR_JSON = "error.json";

    static final String SKU_KEY = "sku";
    static final String INVENTORYQUANTITY_KEY = "inventoryQuantity";
    static final String MARKETABLE_KEY = "marketable";
    static final String NAME_KEY = "name";
    static final String WAREHOUSES_LOCALITY_KEY = "inventory.warehouses.locality";
    static final String WAREHOUSES_QUANTITY_KEY = "inventory.warehouses.quantity";
    static final String WAREHOUSES_TYPE_KEY = "inventory.warehouses.type";
    static final String INVENTORY_QUANTITY_KEY = "inventory.quantity";

    static final String ERROR_FIELD_KEY = "errors.field";
    static final String ERROR_CAUSE_KEY = "errors.cause";

    @LocalServerPort
    protected int port;

    @SpyBean
    protected ProductRepository repository;

    protected File getBodyFromFile(String resourceFile) {
        return new File(getClass().getClassLoader().getResource(resourceFile).getFile());
    }
}
