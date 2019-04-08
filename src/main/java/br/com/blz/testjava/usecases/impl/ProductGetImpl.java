package br.com.blz.testjava.usecases.impl;

import br.com.blz.testjava.domains.Product;
import br.com.blz.testjava.domains.Warehouse;
import br.com.blz.testjava.exceptions.InternalServerErrorException;
import br.com.blz.testjava.exceptions.NotFoundProductException;
import br.com.blz.testjava.gateways.ProductGateway;
import br.com.blz.testjava.gateways.http.converter.ProductConverter;
import br.com.blz.testjava.gateways.http.converter.ProductResponseJSON;
import br.com.blz.testjava.usecases.ProductGet;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ProductGetImpl implements ProductGet {

    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    private static final String NOT_FOUND = "Not found element by sku: ";
    private final ProductGateway gateway;
    private final ProductConverter converter;

    @Override
    public ProductResponseJSON execute(Integer sku) {
        try {
            final Product product = gateway.findById(sku);
            product.getInventory().setQuantity(
                calculateInventoryQuantity(product.getInventory().getWarehouses())
            );
            product.setMarketable(product.getInventory().getQuantity() > 0);
            return converter.convertDomainToResponse(product);
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

    private Integer calculateInventoryQuantity(List<Warehouse> warehouses) {
        return warehouses
            .stream()
            .filter(warehouse -> warehouse.getQuantity() > 0)
            .mapToInt(Warehouse::getQuantity)
            .sum();
    }

}
