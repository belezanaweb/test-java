CREATE TABLE product (
	sku BIGINT PRIMARY KEY ,
	name VARCHAR(250) NOT NULL,
	codigo_inventory BIGINT,
	FOREIGN KEY (codigo_inventory) REFERENCES inventory(codigo)
);

