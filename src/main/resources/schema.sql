drop table if exists users;

create table users(
   id IDENTITY ,
   username VARCHAR(30) not NULL,
   password VARCHAR(30) not NULL,
   email VARCHAR(50) not NULL
);