CREATE TABLE client (client_id serial NOT NULL, name varchar(255) NULL, pictureLeft bytea NULL, pictureMid bytea NULL, pictureRight bytea NULL, PRIMARY KEY (client_id));

CREATE TABLE account (account_id serial NOT NULL, client_id integer NOT NULL, dateBirth date NULL, enabled boolean NULL, name_first varchar(255) NULL, name_last varchar(255) NULL, password varchar(255) NULL, username varchar(255) NULL, PRIMARY KEY (account_id));

CREATE TABLE car (car_id serial NOT NULL, client_id integer NOT NULL, name varchar(255) NULL, PRIMARY KEY (car_id));

ALTER TABLE account ADD FOREIGN KEY (client_id) REFERENCES client (client_id);

ALTER TABLE car ADD FOREIGN KEY (client_id) REFERENCES client (client_id);

CREATE SEQUENCE pk_account INCREMENT 20 START 200;

CREATE SEQUENCE pk_car INCREMENT 20 START 200;

CREATE SEQUENCE pk_client INCREMENT 20 START 200;

INSERT INTO public.client(client_id, name)	VALUES (1, 'default');

INSERT INTO public.account(account_id, client_id, enabled, password, username) VALUES (1, 1, true, '1', 'sa');
