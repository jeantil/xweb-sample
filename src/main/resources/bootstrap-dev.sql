create table users(
  id numeric identity ,
  name varchar(50)
);
insert into users(name) values('john');   -- id 1
insert into users(name) values('jane');   -- id 2
insert into users(name) values('barbie'); -- id 3
insert into users(name) values('ken');    -- id 4
