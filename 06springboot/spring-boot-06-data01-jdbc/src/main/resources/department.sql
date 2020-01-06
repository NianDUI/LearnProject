drop table if exists department;
create table department(
    id int not null auto_increment,
    departmentName varchar(255) default null,
    primary key (id)
)
