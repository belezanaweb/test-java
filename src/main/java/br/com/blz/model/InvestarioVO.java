package br.com.blz.model;

import java.util.List;

import br.com.blz.entity.Deposito;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvestarioVO {
	
	private Integer quantity;
	private List<Deposito> warehouses;

}
