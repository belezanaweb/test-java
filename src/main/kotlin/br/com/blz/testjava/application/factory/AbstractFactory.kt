package br.com.blz.testjava.application.factory

import org.springframework.stereotype.Component

@Component
abstract class AbstractFactory<E, V> {
    abstract fun create(entity: E): V
}
