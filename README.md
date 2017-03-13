# joinfaces-boot-jetty-cdi

- Spring Boot 1.5.1
- Mojarra 2.2.14
- PrimeFaces 6.0
- Jetty 9.4.1.
- CDI 1.2
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
- example link https://peaceful-mountain-96881.herokuapp.com/


# TODO
- inspired by: https://github.com/joinfaces/joinfaces-example