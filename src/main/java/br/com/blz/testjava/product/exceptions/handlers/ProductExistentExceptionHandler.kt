package br.com.blz.testjava.product.exceptions.handlers

import br.com.blz.testjava.product.exceptions.ProductExistentException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ProductExistentExceptionHandler {

  @ExceptionHandler(value = [ProductExistentException::class])
  @ResponseBody
  @ResponseStatus(HttpStatus.CONFLICT)
  @Throws(Exception::class)
  fun handler(req: HttpServletRequest?, e: ProductExistentException) =
    ErrorResponseBean(e.message ?: e.localizedMessage)
}

data class ErrorResponseBean(
  val errorMessage: String
)
