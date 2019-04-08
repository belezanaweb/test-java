package br.com.blz.testjava.usecases.impl;

import static java.util.Objects.nonNull;

import br.com.blz.testjava.exceptions.InternalServerErrorException;
import br.com.blz.testjava.exceptions.NotFoundProductException;
import br.com.blz.testjava.gateways.ProductGateway;
import br.com.blz.testjava.usecases.ProductDelete;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ProductDeleteImpl implements ProductDelete {

    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    public static final String DELETE_OK = "Delete ok";
    private static final String NOT_FOUND = "Not found element by sku: ";
    private final ProductGateway gateway;

    @Override
    public String execute(Integer sku) {
        try {
            if(nonNull(gateway.findById(sku))) {
                gateway.deleteById(sku);
            }
            return DELETE_OK;
        } catch (NotFoundProductException ex) {
            log.error(NOT_FOUND + sku, ex.getMessage());
            throw new NotFoundProductException(NOT_FOUND + sku);
        } catch (InternalServerErrorException ex) {
            log.error(INTERNAL_SERVER_ERROR, ex.getMessage());
            throw new InternalServerErrorException(INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            log.error(INTERNAL_SERVER_ERROR, ex.getMessage());
            throw new InternalServerErrorException(INTERNAL_SERVER_ERROR);
        }
    }

}
