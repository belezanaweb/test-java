package br.com.blz.testjava.service.data.exception;

public class SkuNotProvidedException extends Exception {

    public SkuNotProvidedException() {
        super("sku nao informado para o Produto.");
    }
}
