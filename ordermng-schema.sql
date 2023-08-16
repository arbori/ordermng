drop table if exists ordermng_stock_movement;
drop table if exists ordermng_order_item;
drop table if exists ordermng_order;
drop table if exists ordermng_item;
drop table if exists ordermng_user;

create table ordermng_user (
	user_id int not null generated always as identity,
	user_email varchar(32) not null,
	user_name varchar(40) not null,
	user_active boolean not null,
	constraint ordermng_user_pkey primary key (user_id)
);
create unique index user_email_idx ON ordermng_user (user_email);

create table ordermng_item (
	item_code varchar(12) NOT NULL,
	item_name varchar(40) NOT NULL,
	item_active bool NOT NULL,
	constraint ordermng_item_pkey primary key (item_code)
);
create unique index item_name_idx ON ordermng_item (item_name);

create table ordermng_order (
	order_id int not null generated always as identity,
	order_creation_date timestamp not null,
	order_user_id int not null references ordermng_user (user_id),
	order_shipped boolean not null,
	order_active boolean not null,
	constraint ordermng_order_pkey primary key (order_id)
);

create table ordermng_order_item (
	orderitem_id int not null generated always as identity,
	orderitem_order_id int not null references ordermng_order (order_id),
	orderitem_item_code VARCHAR(12) NOT null references ordermng_item (item_code),
	orderitem_quantity double precision not null,
	constraint ordermng_order_item_pkey primary key (orderitem_id)
);

create table ordermng_stock_movement (
	movement_id int not null generated always as identity,
	movement_creation_date timestamp not null,
	movement_item_code varchar(12) not null references ordermng_item (item_code),
	movement_orderitem_id int references ordermng_order_item (orderitem_id),
	movement_quantity double precision not null,
	movement_active boolean not null,
	constraint ordermng_stock_movement_pkey primary key (movement_id)
);
