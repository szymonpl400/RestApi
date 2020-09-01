CREATE TABLE public."Users"
(
	id serial PRIMARY KEY,
	firstname VARCHAR( 50 ) UNIQUE NOT NULL,
	lastname VARCHAR( 50 ) NOT NULL,
	accountType VARCHAR( 50 ) NOT NULL,
	password VARCHAR( 50 ) NOT NULL,
	email VARCHAR( 50 ) NOT NULL,
	costPerHour int NOT NULL
	
);