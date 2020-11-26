package br.com.blz.testjava.validators;

import br.com.blz.testjava.exception.InvalidProductSKUException;
import br.com.blz.testjava.exception.NullProductException;
import br.com.blz.testjava.entities.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.isNull;

public class Validators {

    private static final Logger logger = LoggerFactory.getLogger(Validators.class);

    public static void isValidSKU(String sku) throws InvalidProductSKUException {

        if (isNull(sku)) {
            logger.error(InvalidProductSKUException.MSG);
            throw new InvalidProductSKUException();
        }
    }

    public static void isValidProduct(Product product) throws NullProductException {

        if (isNull(product)) {
            logger.error(NullProductException.MSG);
            throw new NullProductException();
        }
    }

}
