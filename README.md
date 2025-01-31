# Event Registration

Demo project for ECSE 321 (Introduction to Software Engineering) in the Winter 2025 semester.

The event registration app lets users create and register for events.

## Installation

Running the app locally requires Java and PostgreSQL.

```sh
$ java --version
openjdk 21.0.5 2024-10-15
OpenJDK Runtime Environment (build 21.0.5+11-Ubuntu-1ubuntu122.04)
OpenJDK 64-Bit Server VM (build 21.0.5+11-Ubuntu-1ubuntu122.04, mixed mode, sharing)
$ psql --version
psql (PostgreSQL) 14.15 (Ubuntu 14.15-0ubuntu0.22.04.1)
```

Furthermore, there must be a database for this app.
To create one, simply connect to the database and issue the command `create database event_registration;`:
```
$ psql --username postgres
psql (14.15 (Ubuntu 14.15-0ubuntu0.22.04.1))
Type "help" for help.

postgres=# create database event_registration;
CREATE DATABASE
```
If you want to use a different database configuration (different database name, port, or password), then update [application.properties](./EventRegistration-Backend/src/main/resources/application.properties).

## Running the Tests

To run the tests, move to the `EventRegistration-Backend` directory and then run
```sh
./gradlew clean test
```
