package br.com.blz.testjava.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
open class Warehouse (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    open var id: Long,

    open var quantity: Int,

    @Enumerated(EnumType.STRING)
    open var type: WarehouseType

)