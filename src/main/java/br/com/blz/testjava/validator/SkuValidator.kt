package br.com.blz.testjava.validator

import br.com.blz.testjava.service.ProductService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class SkuValidator(private val productService: ProductService): ConstraintValidator<SkuAvailable, Long> {

    override fun isValid(value: Long, context: ConstraintValidatorContext?): Boolean {
      return try {
        productService.findBySku(value)
        false
      } catch (ex: Exception) {
        true
      }
    }
}
