create table adminstrator
(
 username varchar(8) not null,
 password varchar(8) not null,
 primary key(username)
);
create table designation
(
 code int not null,
 title char(35) not null,
 primary key(code)
);
create table employee
(
 employee_id char(15) not null,
 name char(35) not null,
 gender char(1) not null,
 date_of_birth date not null,
 is_indian boolean not null,
 basic_salary decimal(10,2) not null,
 pan_card_number char(35) not null unique,
 aadhar_card_number char(35) not null unique, 
 designation_code int not null,
 primary key(employee_id),
 foreign key(designation_code) references designation
);
create view EmployeeView as select employee_id,name,gender,date_of_birth,is_indian,basic_salary,
pan_card_number,aadhar_card_number,designation_code,designation.title from employee inner join 
designation on designation.code=employee.designation_code order by employee.name;