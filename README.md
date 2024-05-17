# Project Management System

## Overview

This is a Project Management System built with Java 17 and Spring Boot. It provides a RESTful API for managing projects with CRUD operations.

## Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/shahrukhkh/Project-Management-App-1
2. Navigate to the project directory:

    bash
    cd Project-Management-App-1
3.  Run the application:
    ./mvnw spring-boot:run

4. Access the H2 console:

  URL: http://localhost:8080/h2-console
  JDBC URL: jdbc:h2:mem:test
  Username: sa
  Password: password

5.  Access the Swagger UI for API documentation:

    URL: http://localhost:8080/swagger-ui.html

6. API Endpoints
      POST /projects: Create a new project
      GET /projects: Retrieve all projects
      GET /projects/{id}: Retrieve a project by ID
      PUT /projects/{id}: Update an existing project
      DELETE /projects/{id}: Delete a project by ID    
7.  Testing
    Run unit and integration tests:
    ./mvnw test

