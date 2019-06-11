# PRODUCT API - BELEZA NA WEB

Micro-serviço desenvolvido para o processo seletivo da Beleza na Web.

Sua responsabilidade é manipular produtos mediante criação, busca, edição e deleção.

# Para executá-la localmente:
```
Comando para rodar o redis via docker:
1) docker pull redis:5.0.5
2) docker run -d -p 6379:6379 -i -t redis:5.0.5

Subindo a app via IDE:
3) habilitar annotation processors na IDE
4) executar a classe ApiStarter.java dentro do módulo test-api 
5) acessar em: http://localhost:8083/blz-api/swagger-ui.html
```

# Tecnologias utilizadas:
    - redis para cache dos produtos (TTL está configurado para 1 dia)
    - documentação da api disponível no swagger
      - mockito _(testes unitários)_
        - **ProductResourceTest.java**
        - **ProductServiceTest.java**
        - **ProductCacheTest.java**
      - restAssured _(testes integrados)_
        - para executá-los basta rodar a classe **ProductIntegrationTest.java** dentro de test-api/src/integrationTestApi
        
Para maiores contatos, meu currículo segue visível na raiz do projeto com todos os dados pessoais.         