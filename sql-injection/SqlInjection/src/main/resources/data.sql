--create table if not exists employee (id PRIMARY KEY integer, name VARCHAR(222), password VARCHAR(222), role VARCHAR(222));
insert into employee(name, password, role) values ('Vasiliy Zhozhin', '123', 'MANAGER');
insert into employee(name, password, role) values ('Svetlana Kovaleva', '123', 'MANAGER');
insert into employee(name, password, role) values ('Maria Ivanova', '123', 'MANAGER');
insert into employee(name, password, role) values ('Dmitry Kondratyev', '123', 'STUFF');
insert into flag(flag_value) values ('flag{67686868868}');
