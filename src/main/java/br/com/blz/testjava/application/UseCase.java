package br.com.blz.testjava.application;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIn);
}
