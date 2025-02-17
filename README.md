# Spring Boot Application with MySQL Dockerization

This repository contains a Spring Boot application dockerized along with a MySQL database using Docker Compose.

## Prerequisites

Make sure you have Docker and Docker Compose installed on your machine.

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Running the Application

To run the application locally, follow these steps:

1. Clone this repository to your local machine:
`git clone <repository-url>`

2. Navigate to the project directory:
`cd <project-directory>`

3. Run the following command to start the application and MySQL database:
`gradle deploy`

4. Once the containers are up and running, you can access the Spring Boot application at [http://localhost:8082](http://localhost:8082).

## Stopping the Application

To stop the application and MySQL database containers, run the following command:
`gradle stop`
