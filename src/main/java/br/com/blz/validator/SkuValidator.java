package br.com.blz.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.blz.model.Sku;
import br.com.blz.model.Warehouse;

@Component
public class SkuValidator implements Validator {
	
	@Autowired
	private MessageSource messageSource;

	@Override
	public boolean supports(Class<?> clazz) {
		return Sku.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Sku sku = (Sku) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sku", "required", messageSource.getMessage("application.sku.message.id.mandatory", null, null));
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required", messageSource.getMessage("application.sku.message.name.mandatory", null, null));
		
		if( sku.getSku() != null ) {
			if( !(sku.getSku() > 0) ) {
				errors.rejectValue("sku", "invalidSku", null, messageSource.getMessage("application.sku.message.id.invalid", null, null));
			}
		}
		
		if( sku.getInventory() == null ) {
			errors.rejectValue("inventory", "nullInventory", null, messageSource.getMessage("application.sku.message.inventory.mandatory", null, null));
		}else {
			if( sku.getInventory().getWarehouses() == null ) {
				errors.rejectValue("inventory.warehouses", "nullWarehouses", null, messageSource.getMessage("application.sku.inventory.warehouse.mandatory", null, null));
			}else {
				boolean isErrorLocality = false;
				boolean isErrorQuantity = false;
				boolean isErrorType = false;
				for( Warehouse warehouse : sku.getInventory().getWarehouses() ) {
					if( !isErrorLocality )
						isErrorLocality = StringUtils.isBlank(warehouse.getLocality());
					
					if( !isErrorQuantity )
						isErrorQuantity = !(warehouse.getQuantity() != null && warehouse.getQuantity() > 0);
					
					if( !isErrorType )
						isErrorType = warehouse.getType() == null;  
				}
				
				if( isErrorLocality || isErrorQuantity || isErrorType ) {
					errors.rejectValue("inventory.warehouses", "invalidWarehouses", null, messageSource.getMessage("application.sku.warehouse.invalid", null, null));
				}
			}
		}
	}
	
	

}

