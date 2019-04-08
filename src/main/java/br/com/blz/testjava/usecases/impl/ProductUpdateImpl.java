package br.com.blz.testjava.usecases.impl;

import br.com.blz.testjava.domains.Product;
import br.com.blz.testjava.exceptions.InternalServerErrorException;
import br.com.blz.testjava.exceptions.NotFoundProductException;
import br.com.blz.testjava.gateways.ProductGateway;
import br.com.blz.testjava.gateways.http.converter.ProductConverter;
import br.com.blz.testjava.gateways.http.converter.ProductResponseJSON;
import br.com.blz.testjava.usecases.ProductUpdate;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ProductUpdateImpl implements ProductUpdate {

    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    private static final String NOT_FOUND = "Not found element by sku: ";
    private final ProductGateway gateway;
    private final ProductConverter converter;

    @Override
    public ProductResponseJSON execute(Integer id, Product productUpdate) {
        try {
            Product product = gateway.findById(id);
            product.setInventory(productUpdate.getInventory());
            product.setName(productUpdate.getName());
            return converter.convertDomainToResponse(saveProduct(product));
        } catch (NotFoundProductException ex) {
            log.error(NOT_FOUND, ex.getMessage());
            throw new NotFoundProductException(NOT_FOUND);
        } catch (InternalServerErrorException ex) {
            log.error(INTERNAL_SERVER_ERROR, ex.getMessage());
            throw new InternalServerErrorException(INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            log.error(INTERNAL_SERVER_ERROR, ex.getMessage());
            throw new InternalServerErrorException(INTERNAL_SERVER_ERROR);
        }
    }

    private Product saveProduct(Product product) {
        return Optional
            .ofNullable(gateway.save(product))
            .orElseThrow(
                () -> new InternalServerErrorException(INTERNAL_SERVER_ERROR));
    }

}
