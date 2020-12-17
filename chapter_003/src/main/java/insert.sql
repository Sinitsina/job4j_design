insert into users(name, role_id) values('Ivan', '1');
insert into role(role_name) values('private buyer');
insert into rules(rule_desc) values('prepayment');
insert into item(item_desc, user_id, comment_id, attach_id) values('purchase meat', '1', '1', '1');
insert into comments(comment) values('delivery needed');
insert into attachments(attach_name) values ('payment order');
insert into state(state_desc, item_id) values ('in progress', '1');
insert into category(category_name, item_id) values ('purchase with delivery', '1');
insert into role_rules(role_id, rules_id) values ('1', '1');
