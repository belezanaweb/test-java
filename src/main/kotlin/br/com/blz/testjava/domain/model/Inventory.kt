package br.com.blz.testjava.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.Parent
import javax.persistence.*

@Entity
open class Inventory (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    open var id: Long,

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    open var warehouses: MutableCollection<Warehouse>

) {

    /**
     * <p>Calcula o total de produtos em estoque.</p>
     *
     * @return total de produtos
     *
     * @see Warehouse#quantity
     */
    @JsonProperty("quantity")
    open fun calculateTotalQuantity() = warehouses.parallelStream().mapToInt(Warehouse::quantity).sum()

}