package br.com.blz.testjava.domain.enums;

public enum WarehouseTypeEnum {

    ECOMMERCE(1, "ecommerce"),
    PHYSICAL_STORE(2, "physicalStore");

    private final Integer code;
    private final String friendlyName;

    WarehouseTypeEnum(Integer code, String friendlyName) {
        this.code = code;
        this.friendlyName = friendlyName;
    }

    public Integer getCode() {
        return code;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
