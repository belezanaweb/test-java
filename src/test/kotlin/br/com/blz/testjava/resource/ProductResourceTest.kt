package br.com.blz.testjava.resource

import br.com.blz.testjava.TestJavaApplicationTests
import br.com.blz.testjava.dto.ProductDto
import br.com.blz.testjava.shared.enum.WarehouseType
import br.com.blz.testjava.dto.InventoryDto
import br.com.blz.testjava.dto.WareHouseDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ProductResourceTest : TestJavaApplicationTests() {

    private var mockMvc: MockMvc? = null

    private val mapper = ObjectMapper()
    private val inventory = InventoryDto(0, mutableListOf(
        WareHouseDto("CENTRAL", 10, WarehouseType.ECOMMERCE),
        WareHouseDto("MOEMA", 1, WarehouseType.PHYSICAL_STORE)
    ))
    private val product = ProductDto(500, "Product 001", inventory)
    private val json = mapper.writeValueAsString(product)

    @Autowired
    private lateinit var productResource: ProductResource

    @Before
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productResource).build()
    }

    @Test
    @Throws(Exception::class)
    fun createProductExpectCreated() {

        this.mockMvc!!.perform(MockMvcRequestBuilders.post("/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @Throws(Exception::class)
    fun removeProductExpectNoContent() {

//        this.createProductExpectCreated()

          this.mockMvc!!.perform(MockMvcRequestBuilders
            .delete("/products/{sku}", product.sku)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent)
            .andDo(MockMvcResultHandlers.print())

    }

    @Test
    fun findProductByIdExpectOk() {

//        this.createProductExpectCreated()

        this.mockMvc!!.perform(MockMvcRequestBuilders
            .get("/products/{sku}", product.sku)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(jsonPath("$.inventory.quantity").value("11"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun expectMarketableTrue() {

//        this.createProductExpectCreated()

        this.mockMvc!!.perform(MockMvcRequestBuilders
            .get("/products/{sku}", product.sku)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(jsonPath("$.isMarketable").value(true))
            .andDo(MockMvcResultHandlers.print())
    }

}
