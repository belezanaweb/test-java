create table inventory (
  id                            integer auto_increment not null,
  produto_id                    integer,
  constraint uq_inventory_produto_id unique (produto_id),
  constraint pk_inventory primary key (id)
);

create table produto (
  sku                           integer auto_increment not null,
  name                          varchar(255),
  constraint pk_produto primary key (sku)
);

create table warehouse (
  id                            integer auto_increment not null,
  locality                      varchar(255),
  quantity                      integer not null,
  type                          varchar(255),
  inventory_id                  integer,
  constraint pk_warehouse primary key (id)
);

alter table inventory add constraint fk_inventory_produto_id foreign key (produto_id) references produto (sku) on delete restrict on update restrict;

alter table warehouse add constraint fk_warehouse_inventory_id foreign key (inventory_id) references inventory (id) on delete restrict on update restrict;
create index ix_warehouse_inventory_id on warehouse (inventory_id);

