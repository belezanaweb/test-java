package br.com.blz.testjava.application.validator;

import br.com.blz.testjava.application.dto.product.ProductForm;

import java.util.Objects;

public class ValidaObjetoForm {

    private Boolean validaWarehouseForm;

    public void validaForm(ProductForm productForm) throws NoSuchFieldException {
        if(productForm.getSku() == null || productForm.getSku() <= 0
            || productForm.getName() == null || Objects.equals(productForm.getName(), "")
            || productForm.getInventory() == null ){
            throw new NoSuchFieldException("Campos sku, name e inventory nao podem ser nulos ou vazios");
        }
        if(productForm.getInventory().getWarehouses() == null || productForm.getInventory().getWarehouses().isEmpty()){
            throw new NoSuchFieldException("Warehouses nao pode ser null ou com lista vazia");
        }
        productForm.getInventory().getWarehouses().forEach(warehouseForm -> {
            if(warehouseForm.getLocality() == null || Objects.equals(warehouseForm.getLocality(), "")
                || warehouseForm.getType() == null || Objects.equals(warehouseForm.getType(), "")
                || warehouseForm.getQuantity() == null || warehouseForm.getQuantity() < 0 ){
                validaWarehouseForm = true;
            }
        });

        if(validaWarehouseForm != null && validaWarehouseForm){
            throw new NoSuchFieldException("Campos locality,quantity e type nao podem ser nulos ou vazios e quantity tem que ser positivo");
        }
    }
}
