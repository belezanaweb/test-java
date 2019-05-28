package br.com.blz.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetornoInvestarioVO {
	
	private Integer quantity;
	private List<DepositoVO> warehouses;

}
