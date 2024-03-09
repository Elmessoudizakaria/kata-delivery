# Delivery MVP

This application is delivery api developed with Java 21, Spring 3.2.3 and mvn
respecting the MVC design and the implementing the hateoas principles.

## Features

This MVP provides 4 endpoint to the user

-   Display a list of possible delivery mode.
-   Display list of all delivery slot.
-   Display list of all available delivery slot by delivery mode.
-   Reserve a delivery slot.

## Run the app

The api require JAVA 21 and mvn 3.

Steps to run the api.
- *1*: clone the project
- *2*: access to the project
- *3*: on cmd run `mvn clean install` and then run `mvn spring-boot:run`

the Api will be running on `http://localhost:8080`
To check the api endpoints documentation visit the swagger url [swagger](http://localhost:8080/swagger-ui/index.html)