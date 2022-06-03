# Test-Java

Microsservico desenvolvido para teste da skill Java no Grupo Boticario.

## Dependências
- Redis

## Ambiente local
Para subir o ambiente local você precisa das dependências acima configuradas. Para facilitar temos um docker-compose na raiz do projeto no qual sobe toda a stack.

Se você estiver com o Docker atualizado, o mesmo prove um compose interno. Execute o comando na pasta raiz do projeto:
```shell
$ docker compose up -d
```

Caso contrário efetue a instalação do [docker-compose](https://docs.docker.com/compose/install/), e execute o comando abaixo na pasta raiz do projeto:
```shell
$ docker-compose up -d
```

Com a stack no ar, os acessos são:

- Redis
  - host: localhost
  - port: 16379
  - password: 123456
  
- RedisInsight (Ferramenta para facilitar o acesso ao Redis e execução de comandos)
  - host: [localhost](http://localhost:18001/)
  - port: 18001

### Conexão RedisInsight

Para conectar no redis utilizar os seguintes dados
- Host: redis-server
- Port: 6379
