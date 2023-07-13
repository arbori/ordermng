drop table if exists order_stock_moviment;
drop table if exists ordermng_order;
drop table if exists ordermng_item;
drop table if exists ordermng_user;

create table ordermng_user (
	user_id INT generated always as identity,
	user_name VARCHAR(25) not null,
	user_email VARCHAR(32),
	user_active BOOLEAN not null,

	unique(user_id)
);

create table ordermng_item (
	item_id INT generated always as identity,
	item_name VARCHAR(14) not null,
	item_active BOOLEAN not null,
	
	unique(item_id)
);
create unique index item_name_idx ON ordermng_item (item_name);

create table ordermng_order (
	order_id INT generated always as identity,
	order_creation_date TIMESTAMP not null,
	order_item_id INT not null references ordermng_item (item_id),
	order_quantity INT not null,
	order_user_id INT not null references ordermng_user (user_id),
	order_active BOOLEAN not null,
	
	unique(order_id)
);

create table order_stock_moviment (
	moviment_id INT generated always as identity,
	moviment_creation_date TIMESTAMP not null,
	moviment_quantity INT not null,
	moviment_order_id INT not null references ordermng_order (order_id),
	moviment_active BOOLEAN not null
);
