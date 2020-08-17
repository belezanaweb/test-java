package br.com.blz.testjava.domain.enums;

public enum LocalityEnum {

    SP(1, "sp"),
    MOEMA(2, "moema");

    private final Integer code;
    private final String friendlyName;

    LocalityEnum(Integer code, String friendlyName) {
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
