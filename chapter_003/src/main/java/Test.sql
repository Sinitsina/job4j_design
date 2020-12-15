create database hotel;
create table rooms (
                       id serial primary key,
                       room_Number int,
                       vacant bool,
                       type text
);
insert into rooms(room_Number, vacant, type) values('135', 'true', 'standard');
select *
from rooms;
update rooms set vacant = 'false';
select *
from rooms;
delete from rooms;

select *
from rooms;