# APIServer

This is simple RESTful server with 5 endpoints.

## In order to run it

Environment variables for application.yaml need to be defined.

- DB Credentials
    - SPRING_DATASOURCE_USERNAME
    - SPRING_DATASOURCE_PASSWORD
- DB URI
    - SPRING_DATASOURCE_HOST
    - SPRING_DATASOURCE_PORT
    - DBname - !This is not env variable!
- Root Folder
    - FILES_ROOT - It's a folder where images are stored.

## Technology stack

1. Java 11
2. Spring boot, web, data JPA
3. PostgreSQL db
4. Gradle 

## Extra features

1. Global exception handling
2. Tests with JUnit and Mockito
