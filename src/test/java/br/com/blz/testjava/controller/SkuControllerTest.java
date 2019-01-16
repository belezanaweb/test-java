package br.com.blz.testjava.controller;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.test.context.junit4.SpringRunner;

import br.com.blz.testjava.service.SkuService;
import br.com.blz.testjava.utils.BelezaNaWebStringConfig;
import br.com.blz.testjava.utils.ObjectCreator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SkuControllerTest {
	
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @MockBean
    private SkuService service;

    @Test
    public void shouldNotReturnNull() throws Exception {
    		when(service.getAllSkus()).thenReturn(ObjectCreator.getSkuList());
    	
        assertThat(this.restTemplate.getForObject(BelezaNaWebStringConfig.APPLICATION_BASE_URL + port + "/",String.class))
        		.isNotNull();
    }

}
