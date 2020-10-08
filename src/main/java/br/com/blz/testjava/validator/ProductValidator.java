package br.com.blz.testjava.validator;

import javax.validation.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.blz.testjava.model.Product;
import br.com.blz.testjava.repository.ProductRestRepository;

@Component
public class ProductValidator implements Validator {
	
	@Autowired
	private ProductRestRepository productRestRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		validateConstraints(target, errors);
		validateDuplicatedId(target, errors);
	}

	private void validateConstraints(Object target, Errors errors) {
		Validation.buildDefaultValidatorFactory().getValidator().validate(target)
			.stream().forEach(e -> errors.rejectValue(e.getPropertyPath().toString(), e.getMessageTemplate(), new Object[] {e.getLeafBean().getClass(), e.getPropertyPath(), e.getInvalidValue()} , e.getPropertyPath() + " " + e.getMessage()));
	}

	private void validateDuplicatedId(Object target, Errors errors) {
		Product product = (Product) target;
		if (product.getSku() != null && productRestRepository.findById(product.getSku()).isPresent())
			errors.rejectValue("sku", "duplicated.key", new Object[] {"Product", "sku", product.getSku()}, "Duplicated Key");
	}
}
