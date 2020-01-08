drop table if exists employee;
create table employee(
    id int(11) not null auto_increment,
    lastName varchar(255) default null,
    email varchar(255) default null,
    gender int(2) default null,
    d_id int(11) default null,
    primary key (id)
)
