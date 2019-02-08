# Test-Java

Implementação do Teste Java

Tabela de Conteúdos
=================
* [Pre-Requisitos](#pre-requisitos)
* [Compilar Aplicacao](#compilar-aplicacao)
* [Rodar os testes automatizados](#rodar-os-testes-automatizados)
* [Rodar aplicacao](#rodar-aplicacao)
* [Postman Collection](#postman-collection)
* [Exemplo das operacoes](#exemplo-das-operacoes)


## Pre-Requisitos

Para rodar o projeto você precisará ter o maven 3 e java 8 instalados na sua máquina.

A aplicação e testes irão rodar no endpoint http://localhost:8080

## Compilar Aplicacao

Para compilar a aplicação execute o cli

```
mvn clean install  
```

## Rodar os testes automatizados


```
mvn test
```

## Rodar aplicacao

```
mvn spring-boot:run
```

## Postman Collection

O arquivo [BLZ-WEB.postman_collection.json(https://github.com/fioritti/test-java/blob/master/BLZ-WEB.postman_collection.json) tem as operações CRUDS implementadas


## Exemplo das operacoes

É possível também executar as operações via curl:

1 - Criar Produto

```
curl -X POST \
  http://localhost:8080/products \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d ' {
        "sku": 43264,
        "name": "L'\''Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",
        "inventory": {
            "quantity": 15,
            "warehouses": [
                {
                    "locality": "MOEMA",
                    "quantity": 3,
                    "type": "PHYSICAL_STORE"
                },
                {
                    "locality": "SP",
                    "quantity": 12,
                    "type": "ECOMMERCE"
                }
            ]
        },
        "marketable": true
    }'
```

2 - Criar Produto que já existe com a mesma sku

```
curl -X POST \
  http://localhost:8080/products \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d ' {
        "sku": 43264,
        "name": "L'\''Oréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g",
        "inventory": {
            "quantity": 15,
            "warehouses": [
                {
                    "locality": "MOEMA",
                    "quantity": 3,
                    "type": "PHYSICAL_STORE"
                },
                {
                    "locality": "SP",
                    "quantity": 12,
                    "type": "ECOMMERCE"
                }
            ]
        },
        "marketable": true
    }'
```

3 - Obter todos produtos

```
curl -X GET \
  http://localhost:8080/products/ \
  -H 'cache-control: no-cache'
```
4 - Obter produto por sku

```
curl -X GET \
  http://localhost:8080/products/34 \
  -H 'cache-control: no-cache'
```
5 - Deletar Produto

```
curl -X DELETE \
  http://localhost:8080/products/34 \
  -H 'cache-control: no-cache'
```
6 - Atualizar Produto

```
curl -X PUT \
  http://localhost:8080/products/45 \
  -H 'Content-Type: application/json' \
  -H 'cache-control: no-cache' \
  -d '{
    "sku": 43264,
    "name": "Dove",
    "inventory": {
        "quantity": 2
       
    },
    "marketable": true
}'
```



### Backend Test

Esta é uma avaliação básica de código.

O objetivo é conhecer um pouco do seu conhecimento/prática de RESTful, Spring e Java.

Recomendamos que você não gaste mais do que 4 - 6 horas.

Faça um fork deste repositório que contém o bootstrap de uma aplicação SpringBoot 1.5.12. 

Ao finalizar o teste, submeta um pull request para o repositório que nosso time será notificado.

### Tarefas

Com a seguinte representação de produto:

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

Crie endpoints para as seguintes ações:

- [ ] Criação de produto onde o payload será o json informado acima (exceto as propriedades **isMarketable** e **inventory.quantity**)

- [ ] Edição de produto por **sku**

- [ ] Recuperação de produto por **sku**

- [ ] Deleção de produto por **sku**

### Requisitos


- [ ] Toda vez que um produto for recuperado por **sku** deverá ser calculado a propriedade: **inventory.quantity**
        
        A propriedade inventory.quantity é a soma da quantity dos warehouses

- [ ] Toda vez que um produto for recuperado por **sku** deverá ser calculado a propriedade: **isMarketable**
        
        Um produto é marketable sempre que seu inventory.quantity for maior que 0
        
- [ ] Caso um produto já existente em memória tente ser criado com o mesmo **sku** uma exceção deverá ser lançada 
        
        Dois produtos são considerados iguais se os seus skus forem iguais
        

- [ ] Ao atualizar um produto, o antigo deve ser sobrescrito com o que esta sendo enviado na requisição

        A requisição deve receber o sku e atualizar com o produto que tbm esta vindo na requisição
        
### Dicas

- Os produtos podem ficar em memória, não é necessário persistir os dados
- Sinta-se a vontade para fazer o código em ```groovy``` se preferir, utilizamos bastante aqui
- Testes são sempre bem-vindos :smiley:

