package br.com.blz.testjava.entity;

import java.util.*;

public class Warehouse implements Comparable<Warehouse>{

    private String locality;
    private String type;
    private final Set<Produto> estoque = new HashSet<>();
    private final Map<Long, Produto> produtosNoEstoque = new HashMap<>();

    public Warehouse(String locality, String type) {
        this.locality = locality;
        this.type = type;
    }

    public Warehouse() {

    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void saveProduto(Produto produto) {

        if (this.existeNoEstoque(produto.getSku())){
            throw new ProdutoExistenteException();
        }
        this.estoque.add(produto);
        this.produtosNoEstoque.put(produto.getSku(), produto);
    }

    public void atualizarProduto(Produto produto) {

        if (!this.existeNoEstoque(produto.getSku())){
            throw new RuntimeException();
        }
        this.estoque.add(produto);
        this.produtosNoEstoque.put(produto.getSku(), produto);
    }

    public Set<Produto> produtosNoEstoque(){
        return Collections.unmodifiableSet(this.estoque);
    }

    public Produto findProdutoBySku(Long sku){
        if (!this.produtosNoEstoque.containsKey(sku)) {
            throw new NoSuchElementException();
        }
        return this.produtosNoEstoque.get(sku);
    }

    public boolean existeNoEstoque(Long sku){
        return this.produtosNoEstoque.containsKey(sku);
    }

    public void deletarProduto(Long sku){
        if (!this.produtosNoEstoque.containsKey(sku)) {
            throw new NoSuchElementException();
        }
        Produto produto = this.findProdutoBySku(sku);
        this.estoque.remove(produto);
        this.produtosNoEstoque.remove(sku);
    }

    @Override
    public boolean equals(Object o) {
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(this.locality, warehouse.locality);
    }

    @Override
    public int compareTo(Warehouse o) {
        return 0;
    }
}
