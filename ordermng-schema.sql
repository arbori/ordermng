drop table if exists ordermng_stock_movement;
drop table if exists ordermng_order_item;
drop table if exists ordermng_order;
drop table if exists ordermng_user;
drop table if exists ordermng_item;

CREATE TABLE ordermng_item (
	item_code varchar(12) NOT NULL,
	item_name varchar(40) NOT NULL,
	item_active bool NOT NULL,
	CONSTRAINT ordermng_item_pkey PRIMARY KEY (item_code)
);
create unique index item_name_idx ON ordermng_item (item_name);

create table ordermng_user (
	user_id INT not null generated always as identity,
	user_email VARCHAR(32) not null,
	user_name VARCHAR(40) not null,
	user_active BOOLEAN not null,
	CONSTRAINT ordermng_user_pkey PRIMARY KEY (user_id)
);
create unique index user_email_idx ON ordermng_user (user_email);

create table ordermng_order (
	order_id INT not null generated always as identity,
	order_creation_date TIMESTAMP not null,
	order_user_id INT not null references ordermng_user (user_id),
	order_shipped BOOLEAN not null,
	order_active BOOLEAN not null,
	CONSTRAINT ordermng_order_pkey PRIMARY KEY (order_id)
);

create table ordermng_order_item (
	orderitem_id INT not null generated always as identity,
	orderitem_order_id INT not null references ordermng_order (order_id),
	orderitem_item_code VARCHAR(12) NOT null references ordermng_item (item_code),
	orderitem_quantity DOUBLE PRECISION NOT null,
	CONSTRAINT ordermng_order_item_pkey PRIMARY KEY (orderitem_id)
);

create table ordermng_stock_movement (
	movement_id INT not null generated always as identity,
	movement_creation_date TIMESTAMP not null,
	movement_item_code varchar(12) not null references ordermng_item (item_code),
	movement_orderitem_id INT references ordermng_order_item (orderitem_id),
	movement_quantity INT not null,
	movement_active BOOLEAN not null,
	CONSTRAINT ordermng_stock_movement_pkey PRIMARY KEY (movement_id)
);
