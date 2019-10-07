//package br.com.blz.testjava.controller
//
//import br.com.blz.testjava.repository.ProductsRepository
//import br.com.blz.testjava.service.ProductCrudService
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
//import org.springframework.boot.test.context.TestConfiguration
//import org.springframework.context.annotation.Bean
//import org.springframework.test.web.servlet.MockMvc
//import spock.lang.Specification
//import spock.mock.DetachedMockFactory
//
//@AutoConfigureMockMvc
//@WebMvcTest
//class ProductsController extends Specification {
//
//    @Autowired
//    private MockMvc mvc
//
//    def "when get is performed then the response has status 200 and content is 'Hello world!'"() {
//        expect: "Status is 200 and the response is 'Hello world!'"
//        mvc.perform(get("/api/products"))
//            .andExpect(status().isOk())
//            .andReturn().response
//    }
//
//    @TestConfiguration
//    static class Config {
//        private DetachedMockFactory factory = new DetachedMockFactory()
//
//        @Bean
//        ProductCrudService externalRankingService() {
//            factory.Mock(ProductCrudService)
//        }
//
//        @Bean
//        ProductsRepository rep(){
//            factory.Mock(ProductsRepository)
//        }
//    }
//}
//
