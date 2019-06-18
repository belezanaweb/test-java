### Project description

This project was developed using the Test-Driven Development approach following the architectural standard MVC Model View Controller and implemented using data spring, jpa hibernate and junit.

`mvn spring-boot: run`
 Run the project

- Note: To retain the data it is necessary to change the "ddl-auto" to `ddl-auto: update` which is located resources / application.yml, to restart the database it is recommended to change to` ddl-auto: create` so jpa will re-create the tables.

`mvn test`
Run all project tests except tests that need "id" or "sku", since you need to inform the id / sku in the database to avoid errors.

To verify monitor the data registered in the database h2 access after executing the project
`http://localhost:8080/console`


### Backend Test

[![Build Status](https://travis-ci.com/belezanaweb/test-java.svg?branch=master)](https://travis-ci.com/belezanaweb/test-java)

[![codecov](https://codecov.io/gh/belezanaweb/test-java/branch/master/graph/badge.svg)](https://codecov.io/gh/belezanaweb/test-java)

 

### Tasks

With the following product representation:

```json
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
```

Create endpoints for the following actions:

- [] Product creation where the payload will be the json informed above (except the ** isMarketable ** and ** inventory.quantity ** properties)

- [] Product editing by ** sku **

- [] Product recovery by ** sku **

- [] Deletion of product by ** sku **

### Requirements


- [] Whenever a product is retrieved by ** sku ** the property must be calculated: ** inventory.quantity **

        The inventory.quantity property is the sum of the quantity of the warehouses

- [] Whenever a product is retrieved by ** sku ** the property must be calculated: ** isMarketable **

        A product is marketable whenever its inventory.quantity is greater than 0

- [] If an existing memory product tries to be created with the same ** sku ** an exception must be thrown

        Two products are considered equal if their skus are equal


- [] When updating a product, the old one must be overwritten with what is being sent in the requisition

        The request must receive the sku and update with the product that is also coming in the request
 
