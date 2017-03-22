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

# Tasks TODO
- add JDBC PostgeSQL
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

# THX
- inspired by: https://github.com/joinfaces/joinfaces-example
- nice Heroku tricks: https://github.com/heroku/heroku-buildpack-java
