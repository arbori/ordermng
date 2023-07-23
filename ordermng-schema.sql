drop table if exists ordermng_stock_moviment;
drop table if exists ordermng_order;
drop table if exists ordermng_item;
drop table if exists ordermng_user;

CREATE TABLE public.ordermng_item (
	item_code varchar(12) NOT NULL,
	item_name varchar(40) NULL,
	item_active bool NULL,
	CONSTRAINT ordermng_item_pkey PRIMARY KEY (item_code)
);
create unique index item_name_idx ON ordermng_item (item_name);

create table ordermng_user (
	user_email VARCHAR(32),
	user_name VARCHAR(40) not null,
	user_active BOOLEAN not null,
	CONSTRAINT ordermng_user_pkey PRIMARY KEY (user_email)
);

create table ordermng_stock_moviment (
	moviment_id INT generated always as identity,
	moviment_creation_date TIMESTAMP not null,
	moviment_item_code varchar(12) not null references ordermng_item (item_code),
	moviment_quantity INT not null,
	moviment_active BOOLEAN not null
);

create table ordermng_order (
	order_id INT generated always as identity,
	order_creation_date TIMESTAMP not null,
	order_item_id INT not null references ordermng_item (item_id),
	order_quantity INT not null,
	order_user_id INT not null references ordermng_user (user_id),
	order_active BOOLEAN not null,
	
	unique(order_id)
);
