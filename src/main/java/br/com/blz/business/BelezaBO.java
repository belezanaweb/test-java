package br.com.blz.business;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.blz.entity.Deposito;
import br.com.blz.entity.Produto;
import br.com.blz.mapper.ProdutoMapper;
import br.com.blz.model.ProdutoVO;
import br.com.blz.model.RetornoProdutoVO;
import br.com.blz.service.ProdutoService;


@Component
public class BelezaBO {
	
	@Autowired
	private ProdutoService service;
	
	@Autowired
	private ProdutoMapper produtoMapper;
	
	Integer quantidade = 0;
	
	@Transactional(readOnly = true)
	public ResponseEntity<RetornoProdutoVO> obterProdutoPorSku(Integer sku) {
		Produto produto = service.getById(sku);
		
		if(produto != null) {
			RetornoProdutoVO retorno = produtoMapper.convertEntentyRetornoDTO(produto);
			return ResponseEntity.status(HttpStatus.OK).body(retorno);
		}
		
		return ResponseEntity.notFound().build();
	}

	@Transactional
	public ResponseEntity<?> cadastrarProduto(ProdutoVO produtoVO) {
		
		Produto produto = montarProduto(produtoVO);
		
		Produto produtoSalvo = service.save(produto);
		
		if(produtoSalvo != null ) {
			return ResponseEntity.ok().body(produtoMapper.convertEntentyRetornoDTO(produtoSalvo));
		}
			return ResponseEntity.badRequest().body("Dois produtos são considerados iguais se os seus skus forem iguais");
	}

	private Produto montarProduto(ProdutoVO produtoVO) {
		Produto produto = produtoMapper.convertDTOEntenty(produtoVO);
		
		obterPropriedades(produto);
		
		produto.getInventory().getWarehouses().parallelStream().forEach(item->{
			item.setInventario(produto.getInventory());
		});
		return produto;
	}
	
	@Transactional
	public ResponseEntity<?> editarProduto(ProdutoVO produtoVO) {
		Produto produtoOld = service.getById(produtoVO.getSku());
				
		Produto produtoNew = montarProduto(produtoVO);
		
		produtoOld.setName(produtoNew.getName());
		
		produtoOld.getInventory().setQuantity(produtoNew.getInventory().getQuantity());
		
		List<Deposito> depositoOld = new ArrayList<Deposito>();
		depositoOld.addAll(produtoOld.getInventory().getWarehouses());
		
		List<Deposito> depositoNew = new ArrayList<Deposito>();
		depositoNew.addAll(produtoNew.getInventory().getWarehouses());
		
		for (int i = 0; i < depositoNew.size(); i++) {
			if(depositoOld.size() > 0 && depositoOld.get(i)!= null && depositoNew.get(i)!= null) {
				depositoNew.get(i).setId(depositoOld.get(i).getId());
			}
		}
		
		produtoOld.getInventory().setWarehouses(new HashSet<Deposito>(depositoNew));
		
		produtoOld.getInventory().getWarehouses().parallelStream().forEach(item->{
			item.setInventario(produtoOld.getInventory());
		});
		
		Produto produtoEditado = service.edit(produtoOld);
		
		if(produtoEditado != null ) {
			return ResponseEntity.ok().body(produtoMapper.convertEntentyRetornoDTO(produtoEditado));
		}
			return ResponseEntity.badRequest().body("Produtos inexistente para o sku informado");
	}
	
	@Transactional
	public ResponseEntity<?> deletarProdutoPorSku(Integer sku) {
		service.deleteById(sku);
		Produto excluido = service.getById(sku);
		
		if(excluido == null) {
			return ResponseEntity.status(HttpStatus.OK).body(excluido);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	
	private void obterPropriedades(Produto produto) {
		quantidade = 0;
		//Toda vez que um produto for recuperado por sku deverá ser calculado a propriedade: inventory.quantity
		produto.getInventory().getWarehouses().stream().forEach(deposito -> {
			quantidade= deposito.getQuantity() + quantidade;
		});
		
		produto.getInventory().setQuantity(quantidade);
		
		//Toda vez que um produto for recuperado por sku deverá ser calculado a propriedade: isMarketable
		produto.setMarketable(quantidade > 0 ? true : false);
	}
	
}
