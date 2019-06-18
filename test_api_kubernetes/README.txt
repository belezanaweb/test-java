Para rodar o cluster com o orquestrador de containers kubernetes test-java-api:v1


Ambiente: 

* Ubuntu 18.04 LTS ou Mac com suporte a virtualização
* Instalar o openjdk8, docker-ce, VirtualBox, minikube e o kubectl


Executando:

1 - Executar os passos descritos no arquivo README.txt do docker e, antes de executar o descrito abaixo, pare o container docker.
  * docker stop <ID do container em execução>

2 - No diretório deste arquivo, rodar o comando abaixo para iniciar kubernetes e criar o deployment e service da API no orquestrador de containers Kubernetes:
  * minikube start
  * kubectl create -f deployment.yaml
  * kubectl create -f service.yaml

3 - Rode o comando abaixo para verificar se o POD, Service e Deployment da API foram criados com sucesso:
  * kubectl get pods
  * kubectl get deployments
  * kubectl get services

4 - No diretório deste arquivo, rodar comando para pegar a URL de acesso a API:
  * minikube service service-test-java-api --url

5 - Utilizar os dados abaixo para acessar a API substituindo a URL pela gerada pelo serviço do minikube:

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
