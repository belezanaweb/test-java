package br.com.blz.testjava.entity;

public class ProdutoExistenteException extends RuntimeException {
    public ProdutoExistenteException() {
        super("produto já existe no estoque");
    }
}
