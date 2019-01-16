package br.com.blz.testjava.handler;


import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import br.com.blz.testjava.config.BelezaNaWebStringConfig;
import br.com.blz.testjava.exception.DuplicateSkuExeception;


@ControllerAdvice
public class DefaultExceptionHandler extends DefaultHandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @RequestMapping(produces = { APPLICATION_JSON_UTF8_VALUE })
    @ResponseStatus(value = BAD_REQUEST)
    @ExceptionHandler({DuplicateSkuExeception.class })
    public @ResponseBody ResponseError handleDuplicateSkuException(Exception ex) {
        logger.error("Error bad request ", ex);

        return new ResponseError(HttpStatus.BAD_REQUEST.value(), BelezaNaWebStringConfig.DUPLICATE_SKU_EXCEPTION_MESSAGE);
    }
}