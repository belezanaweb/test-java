Feature: Requisitos básicos para avaliação

  Scenario: Criação de produto
    Given criar ou editar produto:
      | json                                                                                                                                                                                                                                                        |
      | {"sku":43264,"name":"L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g","inventory":{"warehouses":[{"locality":"SP","quantity":12,"type":"ECOMMERCE"},{"locality":"MOEMA","quantity":3,"type":"PHYSICAL_STORE"}]}} |
    When call POST /products
    Then status response 201

  Scenario: Caso um produto já existente em memória tente ser criado com o mesmo sku uma exceção deverá ser lançada
    Given criar ou editar produto:
      | json                                                                                                                                                                                                                                                        |
      | {"sku":43264,"name":"L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g","inventory":{"warehouses":[{"locality":"SP","quantity":12,"type":"ECOMMERCE"},{"locality":"MOEMA","quantity":3,"type":"PHYSICAL_STORE"}]}} |
    When call POST /products
    Then status response 400
    And error "Produto com sku 43264 já cadastrado"

  Scenario: Toda vez que um produto for recuperado por sku deverá ser calculado a propriedade: inventory.quantity
    When call GET /products/43261
    Then status 200
    And sku 43261
    And inventory.quantity 0
    And isMarketable "false"

  Scenario: Toda vez que um produto for recuperado por sku deverá ser calculado a propriedade: isMarketable
    When call GET /products/43260
    Then status 200
    And sku 43260
    And inventory.quantity 0
    And isMarketable "false"

  Scenario: Ao atualizar um produto, o antigo deve ser sobrescrito com o que esta sendo enviado na requisição
    Given criar ou editar produto:
      | json                                                                                                                                                                                                                                                       |
      | {"sku":43264,"name":"L'Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g","inventory":{"warehouses":[{"locality":"SP","quantity":8,"type":"ECOMMERCE"},{"locality":"MOEMA","quantity":2,"type":"PHYSICAL_STORE"}]}} |
    When call PUT /products/43264
    Then status 200
    And sku 43264
    And inventory.quantity 10
    And isMarketable "true"

  Scenario: Deletar um produto
    When call DELETE /products/43264
    Then status response 204
