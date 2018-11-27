package br.com.blz.testjava.util;

import br.com.blz.testjava.exception.BusinessValidationException;
import br.com.blz.testjava.gateways.http.request.ProductDTO;
import br.com.blz.testjava.gateways.http.request.WarehouseDTO;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ValidationUtil {

  private final Validator validator;

  public void validate(ProductDTO productDTO) {
    Set<ConstraintViolation<ProductDTO>> violations = validator.validate(productDTO);
    for (ConstraintViolation<ProductDTO> violation : violations) {
      throw new BusinessValidationException(violation.getMessage());
    }

    if (productDTO.getInventory().getWarehouses() == null
        || productDTO.getInventory().getWarehouses().isEmpty()) {
      throw new BusinessValidationException("Warehouse can not be null!");
    }

    validate(productDTO.getInventory().getWarehouses());
  }

  private void validate(List<WarehouseDTO> warehouseDTOS) {
    warehouseDTOS.forEach(
        warehouseDTO -> {
          Set<ConstraintViolation<WarehouseDTO>> violations = validator.validate(warehouseDTO);
          for (ConstraintViolation<WarehouseDTO> violation : violations) {
            throw new BusinessValidationException(violation.getMessage());
          }
        });
  }
}
