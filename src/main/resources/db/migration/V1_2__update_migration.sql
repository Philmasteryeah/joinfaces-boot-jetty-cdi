CREATE TABLE questionnaire (account_id integer NOT NULL, age numeric NULL, client_id integer NOT NULL, feeling boolean NULL, hobby boolean NULL, name varchar(255) NULL, programming boolean NULL, questionnaire_id serial NOT NULL, version integer NULL, PRIMARY KEY (questionnaire_id));

ALTER TABLE questionnaire ADD FOREIGN KEY (account_id) REFERENCES account (account_id);

ALTER TABLE questionnaire ADD FOREIGN KEY (client_id) REFERENCES client (client_id);

CREATE SEQUENCE pk_questionnaire INCREMENT 20 START 200;
