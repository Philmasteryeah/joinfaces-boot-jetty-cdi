CREATE TABLE client (client_id serial NOT NULL, name varchar(255) NULL, pictureLeft bytea NULL, pictureMid bytea NULL, pictureRight bytea NULL, skin varchar(50) NULL, PRIMARY KEY (client_id));

CREATE TABLE role (client_id integer NOT NULL, name varchar(255) NOT NULL, role_id serial NOT NULL, PRIMARY KEY (role_id));

CREATE TABLE privilege (name varchar(255) NOT NULL, privilege_id serial NOT NULL, PRIMARY KEY (privilege_id));

CREATE TABLE access (access_id serial NOT NULL, flags integer NULL, privilege_id integer NOT NULL, role_id integer NOT NULL, PRIMARY KEY (access_id));

CREATE TABLE account (account_id serial NOT NULL, client_id integer NOT NULL, dateBirth date NULL, enabled boolean NULL, name_first varchar(255) NULL, name_last varchar(255) NULL, password varchar(255) NULL, username varchar(255) NULL, PRIMARY KEY (account_id));

CREATE TABLE account_role (account_id integer NOT NULL, role_id integer NOT NULL, PRIMARY KEY (account_id, role_id));

CREATE TABLE questionnaire (account_id integer NOT NULL, age numeric NULL, client_id integer NOT NULL, feeling boolean NULL, hobby boolean NULL, name varchar(255) NULL, programming boolean NULL, questionnaire_id serial NOT NULL, version integer NULL, PRIMARY KEY (questionnaire_id));

ALTER TABLE role ADD FOREIGN KEY (client_id) REFERENCES client (client_id);

ALTER TABLE access ADD FOREIGN KEY (privilege_id) REFERENCES privilege (privilege_id);

ALTER TABLE access ADD FOREIGN KEY (role_id) REFERENCES role (role_id);

ALTER TABLE account ADD FOREIGN KEY (client_id) REFERENCES client (client_id);

ALTER TABLE account_role ADD FOREIGN KEY (account_id) REFERENCES account (account_id);

ALTER TABLE account_role ADD FOREIGN KEY (role_id) REFERENCES role (role_id);

ALTER TABLE questionnaire ADD FOREIGN KEY (account_id) REFERENCES account (account_id);

ALTER TABLE questionnaire ADD FOREIGN KEY (client_id) REFERENCES client (client_id);

CREATE SEQUENCE pk_access INCREMENT 20 START 200;

CREATE SEQUENCE pk_account INCREMENT 20 START 200;

CREATE SEQUENCE pk_account_role INCREMENT 20 START 200;

CREATE SEQUENCE pk_client INCREMENT 20 START 200;

CREATE SEQUENCE pk_privilege INCREMENT 20 START 200;

CREATE SEQUENCE pk_questionnaire INCREMENT 20 START 200;

CREATE SEQUENCE pk_role INCREMENT 20 START 200;
