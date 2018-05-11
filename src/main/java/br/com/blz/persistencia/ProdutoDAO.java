package br.com.blz.persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.blz.model.DepositoModel;
import br.com.blz.model.ProdutoModel;

@Component
public class ProdutoDAO {
	
	private static Map<Integer, ProdutoModel> DB = new HashMap<>();
	static{
		ProdutoModel p = new ProdutoModel();
		p.setName("L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g");
		p.setSku(43264);
		DepositoModel d = new DepositoModel();
		d.setLocality("SP");
		d.setQuantity(13);
		d.setType("ECOMMERCE");
		DepositoModel d2 = new DepositoModel();
		d2.setLocality("MOEMA");
		d2.setQuantity(2);
		d2.setType("PHYSICAL_STORE");
		p.getInventory().add(d);
		p.getInventory().add(d2);
		DB.put(p.getSku(), p);
		
	}
	public ProdutoModel get(Integer l) {
		return DB.get(l);
	}
	
	public List<ProdutoModel> get() {
		List<ProdutoModel> lista = new ArrayList<>();
		lista=  DB.values().stream().collect(Collectors.toList());
		return lista;
	}
	public void add(ProdutoModel produtoNovo) {
		DB.put(produtoNovo.getSku(), produtoNovo);
		
	}
	public void update(ProdutoModel produtoEditado) {
		DB.put(produtoEditado.getSku(), produtoEditado);
		
	}

	public void delete(ProdutoModel produto) {
		DB.remove(produto.getSku());
		
	}

}
