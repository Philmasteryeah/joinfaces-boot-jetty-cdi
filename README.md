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
CREATE TABLE cars (id integer NOT NULL, name varchar(255) NULL, PRIMARY KEY (id));
CREATE SEQUENCE pk_cars INCREMENT 20 START 200;

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
