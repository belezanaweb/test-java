DROP TABLE IF EXISTS inventory;
DROP TABLE IF EXISTS warehouse;
DROP TABLE IF EXISTS product;

CREATE TABLE inventory (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  quantity INT NOT NULL
);

CREATE TABLE warehouse (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  locality VARCHAR(250) NOT NULL,
  quantity INT NOT NULL,
  type VARCHAR(30) NOT NULL,
  inventory_id INT NOT NULL,
  foreign key (inventory_id) references inventory(id)
);

CREATE TABLE product (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  sku INT NOT NULL,
  name VARCHAR(120) NOT NULL,
  is_marketable BOOLEAN NOT NULL,
  inventory_id INT NOT NULL,
  foreign key (inventory_id) references inventory(id)
);

INSERT INTO inventory(id, quantity) VALUES(1, 15);

INSERT INTO warehouse(id, locality, quantity, type, inventory_id) VALUES(1, 'SP', 12, 'ECOMMERCE', 1);
INSERT INTO warehouse(id, locality, quantity, type, inventory_id) VALUES(2, 'MOEMA', 3, 'PHYSICAL_STORE', 1);

INSERT INTO product(id, sku, name, is_marketable, inventory_id) VALUES(1, 43264, 'LOréal Professionnel Expert Absolut Repair Cortex Lipidium - Máscara de Reconstrução 500g', true, 1);
