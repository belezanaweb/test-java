### Backend Test

Esta é uma avaliação básica de código.

O objetivo é conhecer um pouco do seu conhecimento/prática de RESTful, Spring e Java.

Recomendamos que você não gaste mais do que algumas horas nesse teste.

Faça um fork deste repositório que contém o bootstrap de uma aplicação SpringBoot 1.5.12. 

Ao finalizar o teste, submeta um pull request para o repositório.

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
                "quantity": 12,
                "type": "ECOMMERCE"
            },
            {
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
        
        A propriedade inventory.quantity é a soma da quantity dos inventories

- [ ] Toda vez que um produto for recuperado por **sku** deverá ser calculado a propriedade: **isMarketable**
        
        Um produto é marketable sempre que seu inventory.quantity for maior que 0
        
- [ ] Caso um produto já existente em memória tente ser criado com o mesmo **sku** uma exceção deverá ser lançada 
        
        Dois produtos são considerados iguais se os seus skus forem iguais
        
### Dicas

- Os produtos podem ficar em memória, não é necessário persistir os dados
- Testes são sempre bem-vindos :simple_smile:
