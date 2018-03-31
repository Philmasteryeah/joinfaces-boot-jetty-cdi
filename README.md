[![Heroku](https://heroku-badge.herokuapp.com/?app=ancient-gorge-66249)](https://ancient-gorge-66249.herokuapp.com/login.xhtml)
[![Build Status](https://travis-ci.org/Philmasteryeah/joinfaces-boot-jetty-cdi.svg?branch=master)](https://travis-ci.org/Philmasteryeah/joinfaces-boot-jetty-cdi)
[![Coverage Status](https://coveralls.io/repos/github/Philmasteryeah/joinfaces-boot-jetty-cdi/badge.svg?branch=master)](https://coveralls.io/github/Philmasteryeah/joinfaces-boot-jetty-cdi?branch=master)
 [![Dependency Status](https://www.versioneye.com/user/projects/5abfbd950fb24f4489395eb5/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/5abfbd950fb24f4489395eb5)
 
 
# joinfaces-boot-jetty-cdi
- Java 9
- Spring Boot v2.0.0.M7
- PrimeFaces 6.1
- Jetty 9.4.7.v20170914 
- CDI 2.0 (weld)
- Postgres 42.2.0
- Cayenne 4.1.M1
- javax.faces 2.3.3

![alt text](https://abload.de/img/joinfaces_boot19swo.png)

# Install Joinfaces
- the new joinfaces with spring boot 2.0.0 is not availabe on maven atm
- to start the application you need do the following steps in the GIT bash in your GIT home dir:
```
$ git clone https://github.com/joinfaces/joinfaces.git
$ cd joinfaces
$ mvnw.cmd install
```
- this will install the brand new bloody edge joinfaces in your local maven directory
- now it should start properly
 
# Deployment Heroku
- create a Heroku account
- follow this instructions: https://devcenter.heroku.com/articles/getting-started-with-java#set-up
- download the Heroku CLI from the link and open cmd as admin
```
$ heroku login
$ cd <your project>
$ heroku create
$ git push heroku master 
$ heroku ps:scale web=1
$ heroku open
```
- this example App: https://ancient-gorge-66249.herokuapp.com/login.xhtml
- sa // 1

# Start Apache Cayenne Modeler
- add a new maven run config
- click Workspace and set this project
- set goal to: cayenne-modeler:run
- press run
- select the jdbc driver from maven folder: .m2\repository\org\postgresql\postgresql\...

# Start Apache Cayenne Generate Easy Model
- TOOD

# Postgresql Test shema
- install postgres and run this query
```
DROP TABLE car;

DROP TABLE account;

CREATE TABLE account (account_id INTEGER NOT NULL, dateBirth DATE NULL, name_first VARCHAR(255) NULL, name_last VARCHAR(255) NULL, password VARCHAR(255) NULL, username VARCHAR(255) NULL, PRIMARY KEY (account_id));

CREATE TABLE car (id INTEGER NOT NULL, name VARCHAR(255) NULL, PRIMARY KEY (id));

DROP TABLE AUTO_PK_SUPPORT;

CREATE TABLE AUTO_PK_SUPPORT (TABLE_NAME CHAR(100) NOT NULL, NEXT_ID BIGINT NOT NULL, PRIMARY KEY(TABLE_NAME));

DELETE FROM AUTO_PK_SUPPORT WHERE TABLE_NAME IN ('account', 'car');

INSERT INTO AUTO_PK_SUPPORT (TABLE_NAME, NEXT_ID) VALUES ('account', 200);

INSERT INTO AUTO_PK_SUPPORT (TABLE_NAME, NEXT_ID) VALUES ('car', 200);

````
- you can generate the query for other databaes too
- mvn: cayenne-modeler:run
- Tool -> Generate Database Schema
- choose your adapter

# Configure Eclipse for using lombok
- run: java -jar C:\...\...\.m2\repository\org\projectlombok\lombok\1.16.16\lombok-1.16.16.jar
- wait -> choose eclipse -> install -> quit
- restart eclipse now complete and lombok should work

# Tasks TODO
- more Primefaces Examples working with CDI -> send me a pull request
- model a simple account rights roles db sheme
- add a english german translation

# Tasks completed
- login and redirect with pure spring security without beans 
- programmatic Prime Faces menu and submenu with dynamic <ui:include src="..."/> components 
- some growl messages
- Apache Cayenne Modeler integrated
- Postgres JDBC with Spring works -> JDBC Tempate
- Cayenne Integration completed with orm class using
- lombok integation complete
- menu from menu.property is working

# THX
- great project: https://github.com/joinfaces
- inspired by: https://github.com/joinfaces/joinfaces-example
- nice Heroku tricks: https://github.com/heroku/heroku-buildpack-java
- Cayenne Docs https://cayenne.apache.org/docs/4.1/getting-started-guide/ 
