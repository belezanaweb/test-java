package br.com.blz.testjava;

import br.com.blz.testjava.config.CustomizedResponseEntityExceptionHandler;
import br.com.blz.testjava.controller.ProductImpController;
import br.com.blz.testjava.dto.request.InventoryDTO;
import br.com.blz.testjava.dto.request.ProductRequestDTO;
import br.com.blz.testjava.dto.request.TypeInventoryDTO;
import br.com.blz.testjava.dto.request.WarehouseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
@SpringBootTest(classes = TestJavaApplication.class)
@RunWith(SpringRunner.class)
public class TestJavaApplicationTests {

    private MockMvc mvc;

    @Autowired
    protected ProductImpController productImpController;

    @Autowired
    protected CustomizedResponseEntityExceptionHandler handler;


    public static ProductRequestDTO productMemory;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup(productImpController).build();
        if (productMemory == null) {
            productMemory = this.createRandomProductRequestDTO();
        }
    }

    @Test
    public void acreate() throws Exception {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(productMemory);
        MvcResult result = this.mvc.perform(post("/api/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == 201);
    }

    @Test
    public void bupdate() throws Exception {
        productMemory.setName("Tintura");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(productMemory);
        MvcResult result = this.mvc.perform(put("/api/product/" + productMemory.getSku())
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == 204);
    }

    @Test
    public void cselect() throws Exception {
        MvcResult result = this.mvc.perform(get("/api/product/" + productMemory.getSku())
            .contentType(MediaType.APPLICATION_JSON))
            .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == 200);
    }

    @Test
    public void delete() throws Exception {
        MvcResult result = this.mvc.perform(MockMvcRequestBuilders
            .delete("/api/product/{sku}", productMemory.getSku())
            .contentType(MediaType.APPLICATION_JSON))
            .andReturn();
        Assert.assertTrue(result.getResponse().getStatus() == 204);
    }

    private ProductRequestDTO createRandomProductRequestDTO() {
        Random random = new Random();
        ProductRequestDTO productRequestDTO = new ProductRequestDTO();
        productRequestDTO.setName("teste");
        productRequestDTO.setSku(random.nextInt());
        InventoryDTO inventoryDTO = new InventoryDTO();
        WarehouseDTO warehouseDTO = new WarehouseDTO();
        warehouseDTO.setLocality("Teste 1");
        warehouseDTO.setQuantity(3);
        warehouseDTO.setType(TypeInventoryDTO.ECOMMERCE);
        List<WarehouseDTO> warehouseDTOList = new ArrayList<>();
        warehouseDTOList.add(warehouseDTO);
        inventoryDTO.setWarehouses(warehouseDTOList);
        productRequestDTO.setInventory(inventoryDTO);
        return productRequestDTO;
    }
}
