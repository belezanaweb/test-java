package br.com.blz.testjava.application.presenter

import org.springframework.stereotype.Component

@Component
abstract class AbstractPresenter<E, V> {
    abstract fun present(entity: E): V
}
