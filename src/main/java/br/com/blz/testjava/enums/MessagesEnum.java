package br.com.blz.testjava.enums;

/**
 * Defines messages constants except error messages.
 */
public enum MessagesEnum {

    // errors
    OPERATION_ERROR("Something went wrong when trying to make this operation!"),
    DUPLICATED_PRODUCT_ERROR("A product with this sku already exits!"),
    NO_PRODUCT_ERROR("There isn't any product with this sku: %s"),


    // infos
    SUCESS("This operation was successfully completed!");

    private final String message;

    MessagesEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

