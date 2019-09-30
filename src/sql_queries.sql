drop table if exists delivery;
DROP TABLE IF EXISTS process;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS internal_user;
DROP TABLE IF EXISTS shop;
DROP TABLE IF EXISTS shop;

create table shop ( 
`id` int auto_increment,
lon float NOT NULL,
lat float NOT NULL,
telephone bigint(20) NOT NULL,
primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS internal_user;

-- All internal users
create table internal_user(
`id` int(20) primary key  AUTO_INCREMENT,
`fullname` varchar(60) NOT NULL,
`password` varchar(60) NOT NULL,
`shop_id` int DEFAULT null,
`shop_role` varchar(60) DEFAULT null,
`enabled` tinyint(1) NOT NULL DEFAULT 1
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- employee for temp data on app server
CREATE OR REPLACE VIEW employee AS
SELECT `id`, `fullname`, `shop_id`, `password`
FROM internal_user
WHERE shop_role = 'employee' and enabled = 1 ;
-- mechanic for temp data on app server
CREATE OR REPLACE VIEW mechanic AS
SELECT `id`, `fullname`, `shop_id`, `password`
FROM internal_user
WHERE shop_role = 'mechanic' and enabled = 1;

DROP TABLE IF EXISTS customer;

CREATE TABLE customer(
id int PRIMARY KEY AUTO_INCREMENT,
fullname VARCHAR(45) NOT NULL,
passcode VARCHAR(45) DEFAULT null,
afm BIGINT (10) NOT NULL,
telephone BIGINT (20) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS car;

create table car(
`id` int(10) primary key AUTO_INCREMENT,
`customer_id` int NOT NULL,-- Car belongs to customer
`model` varchar(45) NOT NULL,
`licence_plate` varchar(10) UNIQUE NOT NULL ,
`fuel_type` ENUM ( 'Βενζίνη','Πετρέλαιο','Αέριο'), -- FUEL_OILS 
`first_release` int , -- foramtting in Hibernate...
`status` ENUM ( 'Καλή','Κακή','Μέτρια') DEFAULT NULL,-- CAR_CONDITION
`discount` int,
foreign key (`customer_id`) references customer(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS process;

create table process (
`id` int PRIMARY KEY AUTO_INCREMENT,
`CAR_ID` INT,
`SHOP_ID` INT,
`STATUS` ENUM ('Προς Έγκριση','Προς έλεγχο','Ολοκληρώθηκε') DEFAULT 'Προς έλεγχο',
`Status_Date` DATE, -- foramtting in Hibernate...
FOREIGN KEY (`CAR_ID`) REFERENCES car(`ID`),
FOREIGN KEY (`SHOP_ID`) REFERENCES shop(`ID`)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS delivery;
create table delivery(
id int primary key AUTO_INCREMENT,
process_id int,
lon double,
lat double,
distance bigint,
status ENUM('Δηλώθηκε','Παραλήφθηκε','Εγκρίθηκε') DEFAULT 'Δηλώθηκε',
foreign key (`process_id`) REFERENCES process(`id`)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;

commit;
-- Test Inserts-----------------------------------------------
-- shops
insert into shop (lon,lat,telephone) values(23.73604774725095,38.02286639504322,2108642648),
(22.94337719686689,40.65828573318098,2102222222),
(22.951337993223092,40.688653530177604,2103333333),
(23.665990959857822,38.00720033888227,2104444444),
(23.759870789954903,37.94219353530573,2105555555);

-- Internal user change to encrypted
truncate table `internal_user`;
-- MechPass , EmplPass are the encrypted values
INSERT INTO `internal_user`(fullname, password,shop_id,shop_role) values ("Tsadimas","$2a$04$9JjLBupIkxSMuCowVvTTJum0mCAaayXtaPgchR6Q0fS3wbKK7CC32",1,"mechanic"),
("Lazaros","$2a$04$vovECfzgX.gblu1yVEAO0eJKPVPqjcBjPupDP4O2XDgykTi7mWj56",1,"employee"),
("Stelios","$2a$04$vovECfzgX.gblu1yVEAO0eJKPVPqjcBjPupDP4O2XDgykTi7mWj56",1,"employee"),
("Paylos","$2a$04$vovECfzgX.gblu1yVEAO0eJKPVPqjcBjPupDP4O2XDgykTi7mWj56",1,"employee");

-- ADMIN: admin, godMode96
INSERT INTO `internal_user` (fullname, password,shop_role,enabled) VALUES ('admin','$2a$04$iL0t9PpPU2TcOQI/77G99u2hJxuIcUABopxvJVDBxVi4UsqJKmoIu','admin',1);


INSERT INTO customer (fullname,afm,telephone) VALUES ("Mitsos Plateas",123456789,3905445346);

	INSERT INTO car (customer_id, model, licence_plate, fuel_type, first_release) VALUES (1,"Honda Civic","ARM-1562",'Αέριο',1997);
		INSERT INTO process (CAR_ID,SHOP_ID,status,Status_Date)  VALUES (1,1,'Προς έλεγχο', STR_TO_DATE('6-12-2017', '%d-%m-%Y'));


insert into customer(fullname,passcode,afm,telephone) values ("Koults","pass",789456159,2102353525);
	insert into car (customer_id,model,licence_plate,fuel_type,first_release)values (2,"Amodel","XXX-6969",'Αέριο',2003);
		insert into process(car_id,shop_id,status,Status_date) values (2,1,'Προς Έγκριση', STR_TO_DATE('6-12-2017', '%d-%m-%Y'));
			insert into delivery(process_id,lon,lat,distance,status) values (2,1.523,12.598,40,'Παραλήφθηκε');
	
	insert into car (customer_id,model,licence_plate,fuel_type,first_release)values (2,"SUBARU","SIN-6666",'Αέριο',1999);
		insert into process(car_id,shop_id,status,Status_date) values (3,1,'Προς Έγκριση', STR_TO_DATE('7-2-2017', '%d-%m-%Y'));
			insert into delivery(process_id,lon,lat,distance,status) values (3,21.523,1.5988,50,'Παραλήφθηκε');

commit;

/*
select * from internal_user;
select * from customer;
select * from car;
select * from process;
select * from delivery;
*/
select * from internal_user;
select * from customer;