create table if not exists category (
    id integer not null primary key,
    name varchar(255) not null,
    description text
);

create table if not exists product (
    id integer not null primary key,
    name varchar(255) not null,
    description text,
    stock integer not null,
    price numeric(38, 2) not null,
    category_id integer constraint fk_category_id references category(id)
);

create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;