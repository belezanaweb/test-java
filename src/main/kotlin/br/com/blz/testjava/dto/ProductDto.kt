package br.com.blz.testjava.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.EqualsAndHashCode
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotEmpty

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
class ProductDto(
    @JsonProperty("sku")
    var sku: Long? = null,

    @JsonProperty("name")
    @get:NotEmpty(message = "Nome não pode ser vazio")
    @get:Length(min = 3, max = 200, message = "Nome do produto deve conter entre 3 e 200 caracteres")
    var name: String? = null,

    @JsonProperty("inventory")
    var inventory: InventoryDto? = null
) {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var isMarketable: Boolean? = false
}
