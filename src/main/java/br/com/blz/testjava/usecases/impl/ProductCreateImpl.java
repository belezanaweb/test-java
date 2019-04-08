package br.com.blz.testjava.usecases.impl;

import static java.util.Objects.nonNull;

import br.com.blz.testjava.domains.Product;
import br.com.blz.testjava.exceptions.ConflictException;
import br.com.blz.testjava.exceptions.InternalServerErrorException;
import br.com.blz.testjava.gateways.ProductGateway;
import br.com.blz.testjava.gateways.http.converter.ProductConverter;
import br.com.blz.testjava.gateways.http.converter.ProductResponseJSON;
import br.com.blz.testjava.usecases.ProductCreate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ProductCreateImpl implements ProductCreate {

    public static final String MSG_SKUS_IGUAIS = "Dois produtos são considerados iguais se os seus skus forem iguais";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    private final ProductGateway gateway;
    private final ProductConverter converter;

    @Override
    public ProductResponseJSON execute(Product product) {
        ProductResponseJSON response = null;
        try {
            response = converter.convertDomainToResponse(saveProduct(product));
        } catch (InternalServerErrorException ex) {
            log.error(INTERNAL_SERVER_ERROR, ex.getMessage());
            throw new InternalServerErrorException(INTERNAL_SERVER_ERROR);
        } catch (ConflictException ce) {
            log.error(MSG_SKUS_IGUAIS, ce.getMessage());
            throw new ConflictException(MSG_SKUS_IGUAIS);
        } catch (Exception ex) {
            log.error(INTERNAL_SERVER_ERROR, ex.getMessage());
            throw new InternalServerErrorException(INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    private Product saveProduct(Product product) {
        final Boolean existsProduct = gateway.existsProductBySKU(product.getSku());
        if (nonNull(existsProduct) && !existsProduct) {
            return gateway.save(product);
        } else if(nonNull(existsProduct) && existsProduct){
            throw new ConflictException(MSG_SKUS_IGUAIS);
        } else {
            throw new InternalServerErrorException(INTERNAL_SERVER_ERROR);
        }
    }

}
