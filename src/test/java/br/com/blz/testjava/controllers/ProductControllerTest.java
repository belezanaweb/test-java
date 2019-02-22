package br.com.blz.testjava.controllers;

import br.com.blz.testjava.controllers.dto.request.ProductRequest;
import br.com.blz.testjava.entities.Inventory;
import br.com.blz.testjava.entities.Product;
import br.com.blz.testjava.entities.Type;
import br.com.blz.testjava.entities.Warehouse;
import br.com.blz.testjava.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService serviceMock;

    @Autowired
    ObjectMapper objectMapper;

    private static final String BASE_PATH = "/products/";

    private static final HttpHeaders headers = new HttpHeaders();
    {
        headers.add("Content-Type", "application/json");
    }

    @Ignore
    @Test
    public void shouldInsertAndReturnsResponse200() throws Exception {
        Product productReturn = new Product();
        productReturn.setId(1l);
        productReturn.setSku(1234L);
        productReturn.setName("LOreal Professionnel Expert Absolut Repair Cortex Lipidium");
        productReturn.setInventory(new Inventory());
        List<Warehouse> warehouseList = new ArrayList<>();
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality("SP");
        warehouse.setQuantity(14);
        warehouse.setType(Type.ECOMMERCE);
        warehouseList.add(warehouse);
        productReturn.getInventory().setWarehouses(warehouseList);
        productReturn.getInventory().setQuantity(14);
        productReturn.setIsMarketable(Boolean.TRUE);

        given(serviceMock.findBySku(1234L)).willReturn(Optional.of(productReturn));


        ProductRequest json = new ProductRequest();
        json.setSku(20L);
        json.setName("LOreal Professionnel Expert Absolut Repair Cortex Lipidium");

        mockMvc.perform(post(BASE_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(json)))
            .andDo(print())
            .andExpect(status().isCreated());
    }

    @Test
    public void shouldFindProductBySkuAndReturnsResponse200() throws Exception {
        Product productReturn = new Product();
        productReturn.setId(1l);
        productReturn.setSku(1234L);
        productReturn.setName("LOreal Professionnel Expert Absolut Repair Cortex Lipidium");
        productReturn.setInventory(new Inventory());
        List<Warehouse> warehouseList = new ArrayList<>();
        Warehouse warehouse = new Warehouse();
        warehouse.setLocality("SP");
        warehouse.setQuantity(14);
        warehouse.setType(Type.ECOMMERCE);
        warehouseList.add(warehouse);
        productReturn.getInventory().setWarehouses(warehouseList);
        productReturn.getInventory().setQuantity(14);
        productReturn.setIsMarketable(Boolean.TRUE);

        given(serviceMock.findBySku(1234L)).willReturn(Optional.of(productReturn));

        Long sku = 1234L;
        mockMvc.perform(get(BASE_PATH + sku)
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("sku", is(1234)))
            .andExpect(jsonPath("name", is("LOreal Professionnel Expert Absolut Repair Cortex Lipidium")))
            .andExpect(jsonPath("inventory.warehouses[0].quantity", is(14)))
            .andExpect(jsonPath("inventory.warehouses[0].type", is("ECOMMERCE")))
            .andExpect(jsonPath("inventory.quantity", is(14)))
            .andExpect(jsonPath("isMarketable", is(Boolean.TRUE)));
    }

    @Test
    public void shouldRemoveAndReturnsResponse200() throws Exception {
        Product productInsert = new Product();
        productInsert.setSku(1111);
        productInsert.setName("LOreal Professionnel Expert Absolut Repair Cortex Lipidium");

        Product productReturn = new Product();
        productReturn.setId(1l);
        productInsert.setSku(1111);
        productInsert.setName("LOreal Professionnel Expert Absolut Repair Cortex Lipidium");

        given(serviceMock.insert(productInsert)).willReturn(productReturn);

        Long sku = 1111L;
        mockMvc.perform(delete(BASE_PATH + sku))
            .andDo(print())
            .andExpect(status().isOk());
    }

}
