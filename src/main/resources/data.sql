DROP TABLE IF EXISTS user; 
create table user(id bigint IDENTITY NOT NULL PRIMARY KEY, username varchar(255) UNIQUE NOT NULL ,password varchar(255) NOT NULL);