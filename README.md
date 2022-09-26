# Java Coding Challenge

## Introduction
Given is a Spring Boot application that implements a REST service for manipulating ads and customers. This application is already runnable, but does not do anything useful, yet. Your task is to make it more useful. The scenario of this application is a very simplified version of company.de's domain - we have customers, and these customers want to list ads on our platform.

It is very likely that you will not be able to finish all subtasks in the given time frame. You are basically free to choose which subtasks you work on, but keep in mind that some of the subtasks depend on others. They are listed here in no particular order. Some of the subtasks will have a bigger impact on the overall assessment than others.

You have one week to finish this challenge.

## Tasks
1. The classes in this artifact are all in one package called `de.admanagement`. Create a proper package structure and move the classes to where they belong.
2. Make the domain objects `AdEntity` and `CustomerEntity` persist, either on a local `mysqld` or a local `mongodb`.
3. Currently the application only handles read use cases for ads. Extend the application so that ads can also be created.
4. There is a `CustomerEntity` class in the artifact. Extend the application so that it is also possible to create and delete customers.
5. Make sure that the ad related usecases become customer-aware (meaning that an ad needs a customer in every case).
6. Add validation to the calls that create new entities. The following rules should apply:
   - An ad needs a customer id, a make name, a model name and a category.
   - A customer needs a formally valid email address, a firstname and a lastname composed of alphanumeric characters.
7. Configure logging properly so that log messages are logged to a file.
8. Implement a proper error handling for 404 and 500, with different error messages. Please describe how to reproduce both error statuses.
9. Change the project so that it builds an executable jar.
10. Create a simple HTML/Javascript application that talks to the above REST service. It should be able to list all ads. Making it look pretty would be a plus.
11. If the ad data and the customer data were not accessible from the database but from other RESTful services, what approach would you use to integrate these services? 
12. If you decide not to write tests for your work for reasons of timeboxing, please spend a few minutes to describe what parts of the application you would write tests for, and what aspects these tests would cover, and what role they play in the development process.

Good Luck!

## Tasks status:
1. Because it's a very basic functionality, I've decided to package by layer(api, service, persistence). Another (maybe better) way is to package by feature (ad, customer).
2. done using JPA + H2 (in-memory db).
3. done
4. done
5. added an OneToMany relationship between customer an ad
6. done using javax.validation annotations
7. done using log4j2 config + RollingFile
8. done. 404 is easily reproducible by calling GET /ads/:id or GET /customers/:id with an id of non-existent entity.
9. done. it creates an executable jar with `mvn clean package`
10. [done](http://localhost:8080/swagger-ui/index.html) 
11. in this case we can use aggregator pattern.
12. The tests are not included for all the functionalities and methods(timebox), but they are covering only 1 functionality for each slice (controller, service, persistence), plus an Integration Test which performs the full flow (from api to db). 

## HOW TO:
(assuming you have java and maven installed)
1. run the tests: `mvn clean verify`
2. create an executable jar: `mvn clean package`
3. run the application: `mvn spring-boot:run`
4. test the application: after starting the application (3rd point), just open this [web page](http://localhost:8080/swagger-ui/index.html)

## What we can still improve:
1. authentication & authorization
2. using a real DB
3. caching 
4. logging, tracing
5. testing the functionalities with a real DB (using testcontainers)
6. dockerizing the application and starting with docker-compose or k8s
