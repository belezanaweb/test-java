Para rodar o container docker test-java-api:v1


Ambiente: 

* Ubuntu 18.04 LTS ou Mac
* Instalar o openjdk8 e o docker-ce


Executando:

1 - Ter o aruivo jar do projeto no diretório onde o arquivo dockerfile está

2 - No diretório deste arquivo, rodar o comando abaixo para baixar a imagem e preparar o container para rodar a API:
  * docker build -f test-java-api.dockerfile -t barrosodegas/test-java-api .

3 - No diretório deste arquivo, rodar comando para verificar se a imagem foi criada com sucesso:
  * docker images

4 - No diretório deste arquivo, executar o comando abaixo para criar o container e subir a API:
  * docker run -d -p 8080:8080 test-java-api:v1

5 - No diretório deste arquivo, executar o comando abaixo para ver se o container subiu:
  * docker ps

6 - No diretório deste arquivo, execute o comando abaixo para parar o container após os testes:
  * docker stop <ID do container>

Para acessar a API:

1 - URL e Dados de acesso
  * http://localhost:8080/products definindo no header o content-type: application/json
  * USer: user1, password: user1Pass

2 - Executar as chamadas do item abaixo no Postman ou qualquer ferramenta de sua preferência

3 - URLs para manipulação de produtos pela API no container:
  * Consulta:    GET http://localhost:8080/products/<sku do produto>
  * Remoção:     DELET http://localhost:8080/products/<sku do produto>
  * Adição:      POST http://localhost:8080/products com os dados do produto no body
  * Atualização: PUT http://localhost:8080/products com os dados do produto no body
