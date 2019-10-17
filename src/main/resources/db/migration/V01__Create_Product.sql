
CREATE SCHEMA IF NOT EXISTS blz_products;

CREATE TABLE IF NOT EXISTS blz_products.product (
    sku BIGINT PRIMARY KEY,
    name text NOT NULL,
    marketable boolean   
)


    
