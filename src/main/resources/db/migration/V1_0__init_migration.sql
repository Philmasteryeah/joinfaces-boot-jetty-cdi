CREATE TABLE privilege (name varchar(255) NOT NULL, privilege_id serial NOT NULL, PRIMARY KEY (privilege_id))
;

CREATE TABLE client (client_id serial NOT NULL, name varchar(255) NULL, pictureLeft bytea NULL, pictureMid bytea NULL, pictureRight bytea NULL, skin varchar(50) NULL, PRIMARY KEY (client_id))
;

CREATE TABLE car (car_id serial NOT NULL, client_id integer NOT NULL, name varchar(255) NULL, PRIMARY KEY (car_id))
;

CREATE TABLE role (client_id integer NOT NULL, name varchar(255) NOT NULL, role_id serial NOT NULL, PRIMARY KEY (role_id))
;

CREATE TABLE account (account_id serial NOT NULL, client_id integer NOT NULL, dateBirth date NULL, enabled boolean NULL, name_first varchar(255) NULL, name_last varchar(255) NULL, password varchar(255) NULL, username varchar(255) NULL, PRIMARY KEY (account_id))
;

CREATE TABLE role_privilege (privilege_id integer NOT NULL, role_id integer NOT NULL, role_privilege_id serial NOT NULL, PRIMARY KEY (role_privilege_id))
;

CREATE TABLE questionnaire (account_id integer NOT NULL, age numeric NULL, client_id integer NOT NULL, feeling boolean NULL, hobby boolean NULL, name varchar(255) NULL, programming boolean NULL, questionnaire_id serial NOT NULL, version integer NULL, PRIMARY KEY (questionnaire_id))
;

CREATE TABLE account_role (account_id integer NOT NULL, role_id integer NOT NULL, PRIMARY KEY (account_id, role_id))
;

ALTER TABLE car ADD FOREIGN KEY (client_id) REFERENCES client (client_id)
;

ALTER TABLE role ADD FOREIGN KEY (client_id) REFERENCES client (client_id)
;

ALTER TABLE account ADD FOREIGN KEY (client_id) REFERENCES client (client_id)
;

ALTER TABLE role_privilege ADD FOREIGN KEY (privilege_id) REFERENCES privilege (privilege_id)
;

ALTER TABLE role_privilege ADD FOREIGN KEY (role_id) REFERENCES role (role_id)
;

ALTER TABLE questionnaire ADD FOREIGN KEY (account_id) REFERENCES account (account_id)
;

ALTER TABLE questionnaire ADD FOREIGN KEY (client_id) REFERENCES client (client_id)
;

ALTER TABLE account_role ADD FOREIGN KEY (account_id) REFERENCES account (account_id)
;

ALTER TABLE account_role ADD FOREIGN KEY (role_id) REFERENCES role (role_id)
;

CREATE SEQUENCE pk_account INCREMENT 20 START 200
;

CREATE SEQUENCE pk_account_role INCREMENT 20 START 200
;

CREATE SEQUENCE pk_car INCREMENT 20 START 200
;

CREATE SEQUENCE pk_client INCREMENT 20 START 200
;

CREATE SEQUENCE pk_privilege INCREMENT 20 START 200
;

CREATE SEQUENCE pk_questionnaire INCREMENT 20 START 200
;

CREATE SEQUENCE pk_role INCREMENT 20 START 200
;

CREATE SEQUENCE pk_role_privilege INCREMENT 20 START 200
;


INSERT INTO public.client(client_id, name)	VALUES (1, 'default');

INSERT INTO public.account(account_id, client_id, enabled, password, username) VALUES (1, 1, true, '1', 'sa');
