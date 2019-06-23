package br.com.blz.testjava.domain;

public enum WarehouseType {

    ECOMMERCE(1),
    PHYSICAL_STORE(2);

    private int codigo;

    WarehouseType(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

}
