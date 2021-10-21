DROP database if exists beerservice;

drop user if exists `beer_service`@`%`;

create database if not exists beerservice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

create user if not exists `beer_service`@`%` identified with mysql_native_password BY 'Todoroki@162';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW, 
	CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `beerservice`.* TO `beer_service`@`%`;
    
FLUSH privileges;
