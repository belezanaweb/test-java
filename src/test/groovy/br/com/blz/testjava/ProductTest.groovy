package br.com.blz.testjava

import br.com.blz.testjava.testjava.TestJavaApplication
import br.com.blz.testjava.testjava.controller.ProductController
import br.com.blz.testjava.testjava.model.Inventory
import br.com.blz.testjava.testjava.model.Product
import br.com.blz.testjava.testjava.model.Warehouse
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TestJavaApplication.class)
@AutoConfigureMockMvc
class ProductTest extends Specification {

    @Autowired
    private ProductController productController

    @Autowired
    private MockMvc mockMvc

    def "Initialize Beans injection Test"() {
        expect: "the ProductController is created"
        productController
    }


    def "when POST is performed with product 43264 then the response should be 201 - CREATED"() {

        Product product = mockProduct();


        expect: "Status is 201 - CREATED"
        mockMvc.perform(
            MockMvcRequestBuilders.post("/produto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
            .andExpect(status().isCreated());
    }


    def "when POST is performed with again 43264 then the response should be 400 - BAD REQUEST"() {


        Product product = mockProduct();

        expect: "Status is 400 - BAD REQUEST"
        mockMvc.perform(
            MockMvcRequestBuilders.post("/produto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
            .andExpect(status().isBadRequest());
    }


    def "when PUT is performed with product 43264 then the response show be status 200 - OK"() {


        Product product = mockProduct();
        product.name = "Abcde Test Change product";

        expect: "Status is 200 - OK"
        mockMvc.perform(
            MockMvcRequestBuilders.put("/produto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
            .andExpect(status().isOk());
    }

    def "when PUT is performed with product 234 then the response show be status 404 - NOT FOUND"() {


        Product product = mockProduct();
        product.sku = 234;
        product.name = "Abcde Test Change product";

        expect: "Status is 404 - NOT FOUND"

        mockMvc.perform(
            MockMvcRequestBuilders.put("/produto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
            .andExpect(status().isNotFound());
    }

    def "when GET is performed with product 43264 then the response should be 200 - OK"() {

        expect: "Status is 200 - OK"
        mockMvc.perform(
            MockMvcRequestBuilders.get("/produto/43264")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }


    def "when GET is performed with product 222 then the response should be 404 - NOT FOUND"() {


        expect: "Status is 404"
        mockMvc.perform(
            MockMvcRequestBuilders.get("/produto/222")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }


    def "when DELETE is performed with product 43264 then the response should be 200 - OK"() {


        expect: "Status is 204 - OK"
        mockMvc.perform(
            MockMvcRequestBuilders.delete("/produto/43264")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    static Product mockProduct(){


        Product product = new Product();

        product.sku = 43264l;
        product.name = "Professionnel Expert Absolut Repair Cortex Lipidium";
        product.inventory = new Inventory();
        product.inventory.warehouses = []

        Warehouse warehouse = new Warehouse();

        warehouse.quantity =5;
        warehouse.locality = "SP";
        warehouse.type= "Test";

        product.inventory.warehouses.add(warehouse);

        product;
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
