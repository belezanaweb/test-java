insert into warehouse (id, locality, quantity, type) values (1, 'SP', 12, 'ECOMMERCE');
insert into warehouse (id, locality, quantity, type) values (2, 'MOEMA', 3, 'PHYSICAL_STORE');
insert into warehouse (id, locality, quantity, type) values (3, 'RECIFE', 3, 'PHYSICAL_STORE');
insert into warehouse (id, locality, quantity, type) values (4, 'BV', 0, 'ECOMMERCE');

insert into inventory (id) values (1);
insert into inventory (id) values (2);
insert into inventory (id) values (3);

insert into inventory_warehouses (inventory_id, warehouses_id) values (1, 2);
insert into inventory_warehouses (inventory_id, warehouses_id) values (2, 1);
insert into inventory_warehouses (inventory_id, warehouses_id) values (2, 3);
insert into inventory_warehouses (inventory_id, warehouses_id) values (3, 4);

insert into product (sku, name, inventory_id) values (1, 'Produto Teste', 2);
insert into product (sku, name, inventory_id) values (2, 'Um produto Qualquer', 1);
insert into product (sku, name, inventory_id) values (3, 'Um produto Qualquer', 3);