[![Heroku](https://heroku-badge.herokuapp.com/?app=ancient-gorge-66249)](https://ancient-gorge-66249.herokuapp.com/login.xhtml)
[![Build Status](https://travis-ci.org/Philmasteryeah/joinfaces-boot-jetty-cdi.svg?branch=master)](https://travis-ci.org/Philmasteryeah/joinfaces-boot-jetty-cdi)
[![Coverage Status](https://coveralls.io/repos/github/Philmasteryeah/joinfaces-boot-jetty-cdi/badge.svg?branch=master)](https://coveralls.io/github/Philmasteryeah/joinfaces-boot-jetty-cdi?branch=master)

 
# Joinfaces-boot-jetty-cdi
- Java 10
- Spring Boot v2.0.6
- Spring Security
- Spring REST
- PrimeFaces 6.2
- Jetty 9
- CDI 2.0 (weld)
- Postgres 42.2.0
- Cayenne 4.1.M1
- javax.faces 2.3.7

# Highlights completed
- login and redirect with spring security and cayenne database
- session management with cdi from spring security
- apache cayenne Integration completed with orm class using
- lombok integation
- messages from messages.properties
- all admin stuff in one property
- nice looking responsive layout
- master detail page with list and database saving (TODO)
- simple exception page mapping
- http2

- Login
![alt text](https://abload.de/img/adminloginildcy.png)

- Index
![alt text](https://abload.de/img/adminindexclihl.png)

# Why
- highly modular and customizable
- fat jar deployment with all embedded (~50mb)
- highly portable
- supports Java EE features
	- javax stuff
	- jsf and primefaces
	- component and layout concept
- supports Spring features
	- Security
	- REST (web)

# HTTP2
- connect to https://localhost:9090/
- accept the cert
![alt text](https://abload.de/img/http2f0cct.png)


# Local Deployment
- run mig0 in postgres
- normally you should not need postgres for deploying

```
$ git clone https://github.com/Philmasteryeah/joinfaces-boot-jetty-cdi.git
$ cd joinfaces-boot-jetty-cdi
$ mvn spring-boot:run
```

- open your browser on [http://localhost:9090/index.xhtml](http://localhost:9090/index.xhtml)
- login with sa // test 

# Install milestone Joinfaces (for enthusiasts)  
- the milestone releases of joinfaces are not availabe on maven
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

- or use the maven plugin with mvn heroku:deploy

- this example App: https://ancient-gorge-66249.herokuapp.com/login.xhtml
- sa // 1

# Start Apache Cayenne Modeler
- add a new maven run config
- click Workspace and set this project
- set goal to: cayenne-modeler:run
- press run
- select the jdbc driver from maven folder: .m2\repository\org\postgresql\postgresql\...

# Start Apache Cayenne Generate Easy Model
- very nice tutorial
- http://www.baeldung.com/apache-cayenne-orm

# Postgresql Test shema
- install postgres and run the resources/mig0 query 
- you can generate the query for other databaes too
- mvn: cayenne-modeler:run
- Tool -> Generate Database Schema
- choose your adapter

# Configure Eclipse for using lombok
- run: java -jar C:\...\...\.m2\repository\org\projectlombok\lombok\1.16.16\lombok-1.16.16.jar
- wait -> choose eclipse -> install -> quit
- restart eclipse now complete and lombok should work

# THX
- great project: https://github.com/joinfaces
- inspired by: https://github.com/joinfaces/joinfaces-example
- admin template: https://github.com/adminfaces/admin-starter
- nice Heroku tricks: https://github.com/heroku/heroku-buildpack-java
- Cayenne Docs https://cayenne.apache.org/docs/4.1/getting-started-guide/ 
