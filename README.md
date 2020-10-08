### Instruções para execução local

Rode a aplicação springboot via maven:

```
mvn:spring-boot:run
```

- Ao iniciar a aplicação será feita uma carga de 3 produtos na base. Esses produtos estão no arquivo data/products.json

- A aplicação está com o Swagger-UI implementado. O qual pode ser acessado via http://localhost:8080/swagger-ui/

- A aplicação possui dois metodos de consulta implementados com GraphQL. Os quais podem ser executados via http://localhost:8080/graphql:
  - productById
  - listProducts
  
```json
{
  listProducts {
    sku
		description
		marketable
		inventory {
			quantity
			warehouses {
				locality
				quantity
				type
			}
		}
	}
}

```

