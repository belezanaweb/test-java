package br.com.blz.testjava.config;

public abstract class BelezaNaWebStringConfig {
    /* SKU */
    public static final String SKU_BASE_URL = "/sku";
    
    /* PARAMS */
    public static final String PARAM_SKU = "/{skuId}";
    
    /* URLS */
    public static final String GET_BY_SKU_URL = SKU_BASE_URL + PARAM_SKU;
    
    /* MESSAGES */
    public static final String DUPLICATE_SKU_EXCEPTION_MESSAGE = "SKU ja existe na base";
    
}
