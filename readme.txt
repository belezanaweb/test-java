
[h2database]
para persistir os objetos apenas em memória,
utilizei a dependência h2database 



[restlet]
projeto restlet incluido
copiado na pasta "project-restlet"

restlet é um plugin do chrome que é um client http  

coloque o plugin e importe o json da pasta "project-restlet"
acho que o json pode ser importado pelo postman (outro client mais conhecido)



[testes]

foram feitos chamando o serviço pelo restlet



[scripts de criação da base]

CREATE TABLE produto (
  sku int(11) NOT NULL,
  name varchar(100) NOT NULL,
  PRIMARY KEY (sku)
) 


CREATE TABLE warehouseproductcount (
  sku int(11) NOT NULL,
  locality varchar(20) DEFAULT NULL,
  quantity int(11) DEFAULT NULL,
  type varchar(20) DEFAULT NULL,
  PRIMARY KEY (sku, locality),
  CONSTRAINT fk_produto FOREIGN KEY (sku) REFERENCES produto (sku)
)




