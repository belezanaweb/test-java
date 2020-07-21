package br.com.blz.testjava.product.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WarehouseTest {

    private Validator validator;
    
    private Warehouse defaultWarehouse;

    @BeforeEach
    public void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        defaultWarehouse = Warehouse.builder().idInventory(1L).locality("SP").quantity(10L).type(WarehouseType.ECOMMERCE).build();
    }
    
	@Test
	void mustNotLetCreateWarehouseWithNolocality() {
		defaultWarehouse.setLocality(null);
		
		Set<ConstraintViolation<Warehouse>> violations = validator.validate(defaultWarehouse);
		
		assertEquals(violations.size(), 1);
		
	}
	
	@Test
	void mustNotLetCreateWarehouseWithNoQuantity() {
		defaultWarehouse.setQuantity(null);
		
		Set<ConstraintViolation<Warehouse>> violations = validator.validate(defaultWarehouse);
		
		assertEquals(violations.size(), 1);
		
	}

}
