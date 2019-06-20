CREATE TABLE role (name varchar(255) NOT NULL, role_id serial NOT NULL, PRIMARY KEY (role_id))
;

CREATE TABLE privilege (name varchar(255) NOT NULL, privilege_id serial NOT NULL, PRIMARY KEY (privilege_id))
;

CREATE TABLE role_privilege (role_privilege_id serial NOT NULL, PRIMARY KEY (role_privilege_id))
;

ALTER TABLE role_privilege ADD FOREIGN KEY (role_privilege_id) REFERENCES privilege (privilege_id)
;

ALTER TABLE role_privilege ADD FOREIGN KEY (role_privilege_id) REFERENCES role (role_id)
;

CREATE SEQUENCE pk_privilege INCREMENT 20 START 200
;

CREATE SEQUENCE pk_role INCREMENT 20 START 200
;

CREATE SEQUENCE pk_role_privilege INCREMENT 20 START 200
;

