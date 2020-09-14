package br.com.blz.testjava.utils;

public class Constants {
    public static final String ROOT_PATH = "/api";
    public static final String PRODUCT_PATH = ROOT_PATH + "/product";
    public static final String PRODUCT_SKU_EXISTS_EXCEPTION_MESSAGE = "The product cannot be created because the informed sku already exists.";
    public static final String PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE = "The product with the informed sku does not exists.";
    public static final String PATH_PARAM_SKU = "/{sku}";
}
