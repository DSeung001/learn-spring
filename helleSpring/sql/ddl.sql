drop table if exists member CASCADE;
create table member(
    id int  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(255)
);
insert into member(name) values ("seungryeol");