package br.com.blz.testjava.validator

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [SkuValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class SkuAvailable(
    val message: String,
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
