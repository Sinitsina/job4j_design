insert into role(role_name) values('private buyer');
insert into users(name, role_id) values('Ivan', '1');
insert into rules(rule_desc) values('prepayment');
insert into state(state_desc) values ('in progress');
insert into category(category_name) values ('purchase with delivery');
insert into item(item_desc, user_id, state_id, category_id) values('purchase meat', '1', '1', '1');
insert into comments(comment, item_id) values('delivery needed', '1');
insert into attachments(attach_name, item_id) values ('payment order', '1');
insert into role_rules(role_id, rules_id) values ('1', '1');
