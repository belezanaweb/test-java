package br.com.blz.testjava.product.exceptions.handlers

import br.com.blz.testjava.product.exceptions.ProductNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ProductNotFoundExceptionHandler {

  @ExceptionHandler(value = [ProductNotFoundException::class])
  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @Throws(Exception::class)
  fun handler(req: HttpServletRequest?, e: ProductNotFoundException) =
    ErrorResponseBean(e.message ?: e.localizedMessage)
}
