package br.com.blz.testjava.controller;

import br.com.blz.testjava.domain.api.response.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseController {

    static final String PRODUCT_ROOT = "/products";
    static final String SKU_PLACEHOLDER = "sku";
    static final String SKU_PATH_VARIABLE = "{" + SKU_PLACEHOLDER +"}";
    static final String SKU_PATH = "/" + SKU_PATH_VARIABLE;
    static final String PRODUCT_RESOURCE = PRODUCT_ROOT + SKU_PATH;

    @Autowired
    private ObjectMapper objectMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrors(retieveErrorDetails(ex));

        return ResponseEntity.badRequest().body(errorResponse);
    }

    private List<ErrorResponse.ErrorDetail> retieveErrorDetails(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors()
            .stream()
            .map(this::errorDetail)
            .collect(Collectors.toList());
    }


    private ErrorResponse.ErrorDetail errorDetail(FieldError fieldError) {
        ErrorResponse.ErrorDetail errorDetail = new ErrorResponse.ErrorDetail();

        errorDetail.setField(fieldError.getField());
        errorDetail.setCause(fieldError.getDefaultMessage());

        return errorDetail;
    }

    protected URI getLocation(String sku) {
        try {
            return new URI(getURI(sku));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    protected String getURI(String sku) {
        return PRODUCT_RESOURCE.replace(SKU_PATH_VARIABLE, sku);
    }

    protected void logRequest(String sku, HttpMethod method) {
        logRequest(getURI(sku), method, null);
    }

    protected void logRequest(Object request) {
        logRequest(PRODUCT_ROOT, HttpMethod.POST, request);
    }

    protected void logRequest(String uri, HttpMethod method, Object request) {
        try {
            if (request != null) {
                logger.info("action=request uri={} method={} request={}",
                    uri,
                    method,
                    objectMapper.writeValueAsString(request));
            } else {
                logger.info("action=request uri={} method={}",
                    uri,
                    method);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    protected void logResponse(ResponseEntity responseEntity) {
        logResponse(PRODUCT_ROOT, HttpMethod.POST, responseEntity);
    }

    protected void logResponse(String uri, HttpMethod method, ResponseEntity responseEntity) {
        try {
            if (responseEntity.hasBody()) {
                logger.info("action=response uri={} method={} httpStatus={} response={}",
                    uri,
                    method,
                    responseEntity.getStatusCode().getReasonPhrase(),
                    objectMapper.writeValueAsString(responseEntity.getBody()));
            } else {
                logger.info("action=response uri={} method={} httpStatus={}",
                    uri,
                    method,
                    responseEntity.getStatusCode().getReasonPhrase());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
