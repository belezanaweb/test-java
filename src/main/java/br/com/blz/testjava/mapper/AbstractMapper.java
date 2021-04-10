package br.com.blz.testjava.mapper;

public interface AbstractMapper<T, D> {

	D fromEntityToDto(T entity);

	T fromDtoToEntity(D dto);
}
