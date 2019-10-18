CREATE TABLE warehouse (
 	codigo BIGINT PRIMARY KEY IDENTITY,
	codigo_inventory BIGINT NOT NULL,
	locality VARCHAR(50) NOT NULL,
	quantity BIGINT NOT NULL,
	type VARCHAR(20) NOT NULL,
  FOREIGN KEY (codigo_inventory) REFERENCES inventory(codigo)
);

insert into warehouse(codigo_inventory,locality, quantity, type) values (1,'SP', 12, 'ECOMMERCE');
insert into warehouse(codigo_inventory, locality, quantity, type) values (1,'MOEMA', 3, 'PHYSICAL_STORE');
