package br.com.blz.testjava.domain.usecase

interface UseCase<Input, Output> {
  fun execute(input: Input): Output
}
