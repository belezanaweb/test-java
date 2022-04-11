package br.com.blz.testjava

import br.com.blz.testjava.model.Inventory
import br.com.blz.testjava.model.Product
import br.com.blz.testjava.model.Warehouse
import br.com.blz.testjava.model.responses.InventoryResponse
import br.com.blz.testjava.model.responses.ProductResponse

object TestFixtures {

  private fun requestAsInstance(): Product {
    val warehouses = listOf(
      Warehouse("SP", 12, "ECOMMERCE"),
      Warehouse("MOEMA", 3, "PHYSICAL_STORE")
    )
    val inventory = Inventory(warehouses)
    return Product(
      "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",
      43264, inventory)
  }

  val requestInstanceFixture = requestAsInstance()

  private fun responseAsInstance(): ProductResponse {
    val warehouses = listOf(
      Warehouse("SP", 12, "ECOMMERCE"),
      Warehouse("MOEMA", 3, "PHYSICAL_STORE")
    )
    val inventory = InventoryResponse(15, warehouses)
    return ProductResponse(
      "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",
      43264, inventory, true)
  }

  val responseInstanceFixture = responseAsInstance()

  val requestJsonFixture = """
{
    "sku": 43264,
    "name": "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",
    "inventory": {
        "warehouses": [
            {
                "locality": "SP",
                "quantity": 12,
                "type": "ECOMMERCE"
            },
            {
                "locality": "MOEMA",
                "quantity": 3,
                "type": "PHYSICAL_STORE"
            }
        ]
    }
}
  """

  val responseJsonFixture = """
{
    "sku": 43264,
    "name": "L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",
    "inventory": {
        "quantity": 15,
        "warehouses": [
            {
                "locality": "SP",
                "quantity": 12,
                "type": "ECOMMERCE"
            },
            {
                "locality": "MOEMA",
                "quantity": 3,
                "type": "PHYSICAL_STORE"
            }
        ]
    },
    "isMarketable": true
}
  """


}
