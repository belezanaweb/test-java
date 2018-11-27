package br.com.blz.testjava.controles;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.blz.testjava.MsgException;
import br.com.blz.testjava.entidades.db.Inventory;
import br.com.blz.testjava.entidades.db.Produto;
import br.com.blz.testjava.entidades.db.Warehouse;
import io.ebean.Ebean;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProdutoControle {
  
  
  public List<Produto> buscar(){
    List<Produto> produtos = Ebean.find(Produto.class)
			.fetch("inventory")
			.fetch("inventory.warehouses")
			.findList();
    for(Produto p : produtos) preencherBusca(p);
    return produtos;
  }
  public Produto buscar(int id){
    Produto produto = Ebean.find(Produto.class)
			.fetch("inventory")
			.fetch("inventory.warehouses")
      .where().eq("sku", id)
			.findOne();
    preencherBusca(produto);
    return produto;
  }
  
  
  public void gravar(List<Produto> produtos){
    for(Produto x : produtos) gravar(x);
  }
  public void gravar(Produto... produtos){
    for(Produto x : produtos) gravar(x);
  }
  /**
   * Grava novos Produtos no DB. <br>
   * <br>
   * Caso o produto ainda não exista, mas seja informado um ID/SKU, ele será inserido no DB. <br>
   * Caso esse ID/SKU já exista, uma exceção de chave primária (Primary Key) é retornada do DB, e disparada
   * pelo Ebean. <br>
   * <br>
   * Caso seja informado um Produto sem ID/SKU (na requisição JSON veio sem, no Java terá o valor '0' (zero) ),
   * então será criado pelo DB um ID incremental.
   * @param produto Produto que será adicionado ao DB
   */
  public void gravar(Produto produto){
    if( produto.getInventory() == null ) throw new MsgException("É necessário informar um 'Inventory'!", null);
    
    Ebean.save(produto);
    
    Inventory inventory = produto.getInventory();
    inventory.setProduto(produto);
    Ebean.save(inventory);
    
    if( inventory.getWarehouses() != null ){
      for( Warehouse warehouse : inventory.getWarehouses() ){
        warehouse.setInventory(inventory);
        Ebean.save(warehouse);
      }
    }
    preencherBusca(produto);
  }
  
  /**
   * Este método
   * @param produto Produto que será atualizado
   */
  public void atualizar(Produto produto){
    deletar(produto.getSku());
    gravar(produto);
    preencherBusca(produto);
  }
  
  
  public void deletar(int id){
    Ebean.delete(Produto.class, id);
  }
  
  // --------------------------------------------
  
  /**
   * Método para calcular a quantidade de produtos disponíveis, e verificar se o produto ainda
   * é 'vendável'.
   * 
   * @param produto Produto que será analisado
   */
  private void preencherBusca(Produto produto){
    if( produto == null ) return;
    if( produto.getInventory() == null ) return;
    if( produto.getInventory().getWarehouses() == null ) return;
    
    int quantity = 0;
    for( Warehouse w : produto.getInventory().getWarehouses() ){
      quantity += w.getQuantity();
    }
    produto.getInventory().setQuantity(quantity);
    produto.setMarketable( quantity > 0 );
  }
  
}
