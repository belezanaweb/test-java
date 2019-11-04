package br.com.blz.testjava.base.interfaces;

public interface BaseRepository<ID, Document> {

    Document getById(ID id);
    Document create(Document document);
    Document update(Document document);
    void delete(ID id);

}
