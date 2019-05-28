package br.com.blz.mapper;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.blz.entity.Deposito;
import br.com.blz.entity.Inventario;
import br.com.blz.entity.Produto;
import br.com.blz.model.DepositoVO;
import br.com.blz.model.InventarioVO;
import br.com.blz.model.ProdutoVO;
import br.com.blz.model.RetornoInvestarioVO;
import br.com.blz.model.RetornoProdutoVO;

@Component
public class ProdutoMapper {
	ModelMapper mapper = new ModelMapper();
	
	public Produto convertDTOEntenty(ProdutoVO vo) {
		Produto obj = mapper.map(vo, Produto.class);
		obj.setInventory(convertDTOEntenty(vo.getInventory()));
		return obj;
	}
	
	public Inventario convertDTOEntenty(InventarioVO vo) {
		Inventario obj = mapper.map(vo, Inventario.class);
		obj.setWarehouses(convertListDTO(vo.getWarehouses()));
		return obj;
	}
	
	public Deposito convertDTOEntenty(DepositoVO vo) {
		Deposito obj = mapper.map(vo, Deposito.class);
		return obj;
	}
	
	public Set<Deposito> convertListDTO(List<DepositoVO> vo) {
		return vo.stream().map(o -> {
			return convertDTOEntenty(o);
		}).collect(Collectors.toSet());

	}	
	
	
	public ProdutoVO convertEntentyDTO(Produto obj) {
		ProdutoVO vo = mapper.map(obj, ProdutoVO.class);
		return vo;
	}
	
	public RetornoProdutoVO convertEntentyRetornoDTO(Produto obj) {
		RetornoProdutoVO vo = mapper.map(obj, RetornoProdutoVO.class);
		vo.setInventory(convertEntentyDTO(obj.getInventory()));
		return vo;
	}
	
	public RetornoInvestarioVO convertEntentyDTO(Inventario obj) {
		RetornoInvestarioVO vo = mapper.map(obj, RetornoInvestarioVO.class);
		vo.setWarehouses(convertSetsVO(obj.getWarehouses()));
		return vo;
	}
	
	
	public List<DepositoVO> convertSetsVO(Set<Deposito> obj) {
		return obj.stream().map(o -> {
			return convertEntentyDTO(o);
		}).collect(Collectors.toList());

	}
	
	public DepositoVO convertEntentyDTO(Deposito obj) {
		DepositoVO vo = mapper.map(obj, DepositoVO.class);
		return vo;
	}

}
