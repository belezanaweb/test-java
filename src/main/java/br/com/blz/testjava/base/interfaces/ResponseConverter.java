package br.com.blz.testjava.base.interfaces;

public interface ResponseConverter<From, To> {

    To toResponse(From from);

}
