Create table driver(
id int PRIMARY KEY,
name varchar2(50),
surname varchar2(50),
otch varchar2(50),
birthday date,
sex char(1),
mobilephone char(10)
);
drop table driver;

create table park(
id int PRIMARY KEY,
name varchar2(100),
adress varchar2(150)
);

create table driverInPark(
id int PRIMARY KEY,
driver_id int,
carDriveonPark int,
FOREIGN KEY(driver_id) REFERENCES driver(id),
FOREIGN KEY(carDriveonPark) REFERENCES carsOnPark(id)
);
drop table driverInPark;

create table car(
id int PRIMARY KEY,
model varchar2(80),
color varchar2(30),
year date
);
drop table car;

create table carsOnPark(
id int PRIMARY KEY,
car_id int,
park_id int,
FOREIGN KEY(car_id) REFERENCES CAR(id),
FOREIGN KEY(park_id) REFERENCES PARK(id),
UNIQUE (car_id)
);
drop table carsOnPark;

select * from car;
insert into car values(1,'Lanos', 'Green', TO_DATE('20150505','yyyymmdd'));
insert into car values(2,'Lanos', 'Red', TO_DATE('20100505','yyyymmdd'));
insert into car values(3,'Lanos', 'Blue', TO_DATE('20130505','yyyymmdd'));
insert into car values(4,'Lanos', 'Grow', TO_DATE('20090505','yyyymmdd'));

select * from driver;
insert into driver values(1,'Ivan', 'Ivanov', 'Ivanovich',TO_DATE('19540505','yyyymmdd'),'f','0193223432');
insert into driver values(2,'Petr', 'Petrov', 'Petrovich',TO_DATE('19540505','yyyymmdd'),'f','1013232132');
insert into driver values(3,'Vladyslav', 'Vladyslavovich', 'Vladyslavovich',TO_DATE('19540505','yyyymmdd'),'f','1013232132');

select * from park;
insert into park values (1,'Park1','Simkova123');
insert into park values (2,'Park2','Vasylieva123');
insert into park values (3,'Park3','Dimitrova123');

select * from DRIVERINPARK;
insert into driverinpark values (1,1,1);
insert into driverinpark values (2,1,2);
insert into driverinpark values (3,1,3);
insert into driverinpark values (4,2,1);
insert into driverinpark values (5,2,4);
insert into driverinpark values (6,2,2);

select * from carsonpark;
insert into carsonpark values (1,1,1);
insert into carsonpark values (2,2,1);
insert into carsonpark values (3,3,1);
insert into carsonpark values (4,4,2);

select * from carsonpark csp
inner join car cars on csp.CAR_ID = cars.id
inner join driverinpark dp on dp.CARDRIVEONPARK=csp.ID ;

select * from driver where id in (
select driver_id from driverinpark dp
inner join carsonpark crp on crp.ID = dp.CARDRIVEONPARK
inner join car on crp.CAR_ID = car.id
where crp.PARK_ID = 2 and car.YEAR<TO_DATE('20100101','yyyymmdd'));
