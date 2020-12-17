create table users(
    id serial primary key,
    name varchar(50),
    role_id int references role(id)
);

create table role(
    id serial primary key,
    role_name varchar(50)
);

create table rules(
    id serial primary key,
    rule_desc varchar(500)
);

create table item(
    id serial primary key,
    item_desc varchar(300),
    user_id int references users(id),
    comment_id int references comments(id),
    attach_id int references attachments(id)
);

create table comments(
    id serial primary key,
    comment varchar(500)
);

create table attachments(
    id serial primary key,
    attach_name varchar(100)
);

create table state(
    id serial primary key,
    state_desc text,
    item_id int references item(id)
);

create table category(
    id serial primary key,
    category_name varchar(50),
    item_id int references item(id)
);

create table role_rules(
    id serial primary key,
    role_id int references role(id),
    rules_id int references rules(id)
);