
# Alterações na estrutura

No arquivo `pom.xml` foi alterado: 
- Removido da implemenatção padrão do **Spring** o servidor `Tomcat`, e adicionado o `Undertow`
- Adicionado `Lombok` *(reduzir a quantidade de 'código padrão' que precisa ser criado)* 
- Adicionado `EBean` para persistência em banco de dados
- Adicionado `LogBack` e `SLF4J` para logs
- Adicionado banco de dados em memória `H2`

Para compilar basta executar `mvn clean package`.
Para executar `java -jar ./target/blz-api.jar`.

