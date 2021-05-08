drop table if exists tests;
create table tests (
  id int primary key,
  name varchar(20) not null,
  password varchar(10) not null
);