CREATE TABLE product (
	sku NUMERIC PRIMARY KEY,
	name text NOT NULL,
	creation_date TIMESTAMP NOT NULL default now()
);
CREATE UNIQUE INDEX uidx_sku ON product (sku);

CREATE TABLE warehouse (
	id SERIAL PRIMARY KEY,
	product_id INTEGER REFERENCES product (sku),
	locality text NOT NULL,
	quantity numeric,
	type  text,
	creation_date TIMESTAMP NOT NULL default now()
);