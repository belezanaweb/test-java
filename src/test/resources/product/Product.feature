# language: pt

Funcionalidade: Cadastro de Produtos

Esquema do Cenario: Consulta de produto com suscesso

	Dado o sku do produto "<sku>"
	Quando realizada uma consulta em que o produto é encontrado com sucesso 
	Então a api devera retornar <statusCode>  
	
Exemplos:
|sku|statusCode|
|1  |200		   |


Esquema do Cenario: Validação da quantidade em estoque

	Dado o sku do produto "<sku>"
	Quando realizada uma consulta em que o produto é encontrado com sucesso 
	Então a api devera retornar <statusCode> 
	E a quantidade em estoque devera ser <quantity> 
	
Exemplos:
|sku|statusCode|quantity|
|1  |200		   |2			  |

Esquema do Cenario: Validação de produto negociavel

	Dado o sku do produto "<sku>"
	Quando realizada uma consulta em que o produto é encontrado com sucesso 
	Então a api devera retornar <statusCode> 
	E o atributo que indica se o produto é negociavel devera ser "<marketable>" 
	
Exemplos:
|sku|statusCode|marketable|
|1  |200		   |true			|


Esquema do Cenario: Criar produto com o mesmo SKU 

	Dado o sku do produto "<sku>"
	Quando realizada uma requisição para criar um novo produto com o sku de um produto já existente 
	Então a api devera retornar <statusCode> 
	
Exemplos:
|sku|statusCode| 
|1  |409		   |

Esquema do Cenario: Atualizar produto com novo nome e sku

	Dado o sku do produto "<sku>"
	Quando realizar uma requisição para atualizar o nome de um produto
	Então a api devera retornar <statusCode>
	E o novo nome deverá ser "<nome>"
 	
Exemplos:
|sku|statusCode|nome						|
|1  |200		   |Sabonete liquido|