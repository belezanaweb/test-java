CREATE SEQUENCE IF NOT EXISTS blz_products.sq_warehouse_id
  start 1
  increment 1;

CREATE TABLE IF NOT EXISTS blz_products.warehouse (
	warehouse_id BIGINT PRIMARY KEY DEFAULT NEXTVAL('sq_warehouse_id'),
	inventory_id BIGINT,
    locality VARCHAR(100) NOT NULL,
    quantity Int NOT NULL,
    type text NOT NULL,
    FOREIGN KEY (inventory_id) REFERENCES blz_products.inventory (inventory_id)
    
) 
