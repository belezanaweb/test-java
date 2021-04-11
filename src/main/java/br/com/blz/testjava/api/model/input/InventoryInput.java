package br.com.blz.testjava.api.model.input;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.blz.testjava.model.Warehouse;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class InventoryInput {

	@NotNull
	private List<Warehouse> warehouses = new ArrayList<>();
}
