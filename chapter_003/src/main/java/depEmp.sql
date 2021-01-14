1. Создать таблицы и заполнить их начальными данными
create table departments(
                            id serial primary key,
                            name varchar(10)
);

create table employees(
                          id serial primary key,
                          name varchar(10)
);

insert into departments(name) values ('Sales');
insert into employees(name) values ('Engineer');
insert into departments(name) values ('Sales 1');
insert into departments(name) values ('Sales 2');
insert into departments(name) values ('Sales 3');
insert into departments(name) values ('Sales 4');
insert into employees(name) values ('Engineer 1');
insert into employees(name) values ('Engineer 2');
insert into employees(name) values ('Engineer 3');
insert into employees(name) values ('Engineer 1');
insert into departments(name) values ('Sales 5');
insert into employees(name) values ('Name');
insert into employees(name) values ('Name 1');
insert into employees(name) values ('Name 2');

2. Выполнить запросы с left, rigth, full, cross соединениями
select *
from departments d
         left join employees e
                   on d.id = e.id;

select *
from departments d
         right join employees e
                    on d.id = e.id;

select *
from departments d
         full join employees e
                   on d.id = e.id;

select *
from departments d
         cross join employees e;

3. Используя left join найти работников, которые не относятся ни к одну из департаментов
select *
from employees e
         left join departments d
                   on e.id = d.id;


4. Используя left и right join написать запросы, которые давали бы одинаковый результат.

select *
from employees e
left join departments d
on e.id = d.id;

select *
from departments d
         right join employees e
                    on e.id = d.id;

5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары

create table teens(
                      id serial primary key,
                      name varchar(10),
                      gender varchar(1)
);

insert into teens(name, gender) VALUES ('Ivan', 'm');
insert into teens(name, gender) VALUES ('Max', 'm');
insert into teens(name, gender) VALUES ('Anna', 'f');
insert into teens(name, gender) VALUES ('Sveta', 'f');
insert into teens(name, gender) VALUES ('Masha', 'f');
insert into teens(name, gender) VALUES ('Stas', 'm');
insert into teens(name, gender) VALUES ('Yana', 'f');
insert into teens(name, gender) VALUES ('Dasha', 'f');
insert into teens(name, gender) VALUES ('Alex', 'm');
insert into teens(name, gender) VALUES ('Fedor', 'm');

select t1.name AS name1, t2.name AS name2
from teens t1
         cross join teens t2
where t1.gender != t2.gender;