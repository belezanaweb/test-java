package br.com.blz.testjava.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.blz.testjava.TestJavaApplication;
import br.com.blz.testjava.dto.SKUDTO;
import br.com.blz.testjava.repository.SKURepository;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(classes = {TestJavaApplication.class})
public class TestProductService {

	@Autowired
	SKURepository skuRepo;
	
	@After
	public void initRepository() {
		skuRepo.list = new HashMap<Integer, SKUDTO>();
	}
	
	@Test
	public void testProductExist() {
		SKUDTO skuDTO = new SKUDTO();
		skuDTO.setSku(999);
		skuRepo.list.put(1, skuDTO);
		assertNotNull(skuRepo.getSKU(1));
	}
	

	@Test
	public void testProductNotExist() {
		assertNull(skuRepo.getSKU(10));
	}
}
