# joinfaces-boot-jetty-cdi

- Spring Boot 1.5.1
- Mojarra 2.2.14
- PrimeFaces 6.0
- Jetty 9.4.1.
- CDI 1.2 (weld)
- Omnifaces	1.14


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
- get the menu working with the menu.yml file for crating and easy manipulating the menu structure
- add a english german translation
- add a Class for easy reading properties from the property file

# Tasks completed
- login and redirect with pure spring security without beans 
- programmatic Prime Faces menu and submenu with dynamic <ui:include src="..."/> components 
- some growl messages
- Apache Cayenne Modeler integrated
- Postgres JDBC with Spring works -> JDBC Tempate
- Cayenne Integration completed with orm class using
- lombok integation complete

# THX
- inspired by: https://github.com/joinfaces/joinfaces-example
- nice Heroku tricks: https://github.com/heroku/heroku-buildpack-java
- Cayenne Docs https://cayenne.apache.org/docs/4.0/cayenne-guide/starting-cayenne.html 
