CREATE SEQUENCE IF NOT EXISTS blz_products.sq_inventory_id
  start 1
  increment 1;
  
CREATE TABLE IF NOT EXISTS blz_products.inventory (
	inventory_id BIGINT PRIMARY KEY DEFAULT NEXTVAL('sq_inventory_id'), 
    product_id BIGINT UNIQUE, 
    quantity Int,   
    marketable BOOLEAN,
    FOREIGN KEY (product_id) REFERENCES blz_products.product (sku)
)
    
