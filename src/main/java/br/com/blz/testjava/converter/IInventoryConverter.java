package br.com.blz.testjava.converter;

import br.com.blz.testjava.api.v1.model.InventoryDTO;
import br.com.blz.testjava.domain.Inventory;

public interface IInventoryConverter extends IDomainConverter<InventoryDTO, Inventory> {
}
