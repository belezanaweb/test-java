package br.com.blz.testjava.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class ProductValidationTest {
	
	private Validator validator;
	
	@BeforeAll
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
	
	@Test
	void constraintsShouldBeEmpty() {
		assertTrue(validator.validate(ProductMock.getNewProduct()).isEmpty());
	}
	
	@Test
	void shouldHaveViolation_whenNoId() {
		Set<ConstraintViolation<Product>> constraints = validator.validate(ProductMock.getNewProductWithoutId());
		assertTrue(constraints.stream()
			.anyMatch(c -> "sku".equals(c.getPropertyPath().toString()) 
					&& "{javax.validation.constraints.NotNull.message}".equals(c.getMessageTemplate())));
	}
	
	@Test
	void shouldHaveViolation_whenNoDescription() {
		Set<ConstraintViolation<Product>> constraints = validator.validate(ProductMock.getNewProductWithoutDescription());
		assertTrue(constraints.stream()
			.anyMatch(c -> "description".equals(c.getPropertyPath().toString()) 
					&& "{javax.validation.constraints.NotBlank.message}".equals(c.getMessageTemplate())));
	}
	
}
