package br.com.blz.testjava.domain.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
open class Product (

    @Id
    open var sku: Long,

    @Column(nullable = false)
    open var name: String,

    @OneToOne(cascade = [CascadeType.ALL])
    open var inventory: Inventory

) {

    /**
     * <p>Verifica se o produto e comercializavel.</p>
     *
     * @return {@code true} se o produto for comercializavel
     *
     * @see Inventory#quantity
     */
    @JsonProperty("isMarketable")
    open fun isMarketable() = inventory != null && inventory.calculateTotalQuantity() > 0

}