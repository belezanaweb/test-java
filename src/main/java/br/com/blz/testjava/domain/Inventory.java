package br.com.blz.testjava.domain;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class Inventory {
	private Set<Warehouse> warehouses;
}
