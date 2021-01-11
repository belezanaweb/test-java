package br.com.blz.testjava.mapper;

import java.math.BigDecimal;

import br.com.blz.testjava.dto.ProdutoDTO;
import br.com.blz.testjava.dto.ProdutoSalvamentoDTO;
import br.com.blz.testjava.model.Produto;

public class ProdutoMapper {

	private ProdutoMapper() {
	}

	public static Produto toEntity(ProdutoSalvamentoDTO produtoDTO) {

		Produto produto = new Produto();
		produto.setName(produtoDTO.getName());
		produto.setSku(produtoDTO.getSku());
		produto.setInventory(InventoryMapper.toEntity(produtoDTO.getInventory()));

		return produto;
	}

	public static ProdutoDTO toProdutoDTO(Produto produto) {

		ProdutoDTO dto = new ProdutoDTO();
		dto.setName(produto.getName());
		dto.setSku(produto.getSku());
		dto.setInventory(InventoryMapper.toDTO(produto.getInventory()));
		dto.setIsMarketable(dto.getInventory().getQuantity().compareTo(BigDecimal.ZERO)==1);

		return dto;
	}

}
