package br.com.blz.testjava.base.interfaces;

public interface RequestConverter<From, To> {

    To toRequest(From from);


}
