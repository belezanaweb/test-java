DROP TABLE IF EXISTS Produto;
DROP TABLE IF EXISTS Inventario;
DROP TABLE IF EXISTS Deposito;

CREATE TABLE IF NOT EXISTS Inventario (
  id_inventario              INTEGER     NOT NULL PRIMARY KEY,
  quantity       INTEGER,
  --id_warehouses     INTEGER,
  --FOREIGN KEY(id_warehouses) REFERENCES Deposito(id_deposito)
);



CREATE TABLE IF NOT EXISTS Deposito (
  id_deposito             INTEGER     NOT NULL PRIMARY KEY,
  locality        VARCHAR(100) NOT NULL,
  quantity     INTEGER,
  type			 VARCHAR(50) NOT NULL,
  id_inventory     INTEGER,
  FOREIGN KEY(id_inventory) REFERENCES Inventario(id_inventario)
);


 
CREATE TABLE IF NOT EXISTS Produto (
  sku              INTEGER     NOT NULL PRIMARY KEY,
  name       	  VARCHAR(50) NOT NULL,
  id_inventory     INTEGER,
  isMarketable			  BOOLEAN,
  FOREIGN KEY(id_inventory) REFERENCES Inventario(id_inventario)
);



