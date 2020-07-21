package br.com.blz.testjava.product.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest {

    private Validator validator;
    
    private Product defaultProduct; 

    @BeforeEach
    public void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        defaultProduct = Product.builder().sku(123456L).name("L'oreal").build();
    }

	@Test
	void mustNotLetCreateProductWithNoSKU() {
		defaultProduct.setSku(null);
		
		Set<ConstraintViolation<Product>> violations = validator.validate(defaultProduct);
		
		assertEquals(violations.size(), 1);
		
	}

	@Test
	void mustNotLetCreateProductWithoutName() {
		defaultProduct.setName(null);
		
		Set<ConstraintViolation<Product>> violations = validator.validate(defaultProduct);
		
		assertEquals(violations.size(), 1);
		
	}

}
