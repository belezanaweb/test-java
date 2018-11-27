alter table inventory drop constraint if exists fk_inventory_produto_id;

alter table warehouse drop constraint if exists fk_warehouse_inventory_id;
drop index if exists ix_warehouse_inventory_id;

drop table if exists inventory;

drop table if exists produto;

drop table if exists warehouse;

