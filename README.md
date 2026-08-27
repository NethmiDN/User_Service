# User Service

Spring Boot microservice for managing users, favorite destinations, and visited places in the My Memories application.

## Overview

This service provides a REST API for:

- creating and managing users
- storing favorite destinations per user
- tracking visited places per user
- exposing user-related data for downstream services

It is built with Java 21 and Spring Boot 3.4.x, and it registers itself with Eureka as a discovery client.

## Tech Stack

- Java 21
- Spring Boot 3.4.3
- Spring Web
- Spring Data JPA
- MySQL
- Spring Cloud Netflix Eureka Client
- Spring Cloud Config Client
- Validation
- Lombok
- Maven

## Project Structure

```text
user-service/
├── src/
│   ├── main/
│   │   ├── java/com/example/userservice/
│   │   │   ├── controller/
│   │   │   ├── entity/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── UserServiceApplication.java
│   │   └── resources/
│   │       └── application.yml
│   └── test/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
└── README.md
```

## Prerequisites

Before running the project, make sure you have:

- Java 21+
- Maven or the provided Maven wrapper
- MySQL database available
- Spring Cloud Config Server running at `http://localhost:8888` (optional import in config)
- Eureka server available for service discovery

## Configuration

The application uses `src/main/resources/application.yml`:

```yaml
spring:
  application:
    name: user-service
  config:
    import: optional:configserver:http://localhost:8888
```

This means the app will try to fetch external configuration from a Spring Cloud Config Server. If no config server is available, startup will still continue because the import is marked as optional.

## Running the Application

### Using Maven wrapper

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
mvnw.cmd spring-boot:run
```

### Build the application

```bash
./mvnw clean package
```

## API Endpoints

Base path: `/api/users`

### Users

- `POST /api/users` - Create a user
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get a user by ID
- `GET /api/users/email/{email}` - Get a user by email
- `PUT /api/users/{id}` - Update a user

### Favorites

- `POST /api/users/{userId}/favorites?destinationId={destinationId}` - Add a favorite destination
- `GET /api/users/{userId}/favorites` - Get user favorites
- `DELETE /api/users/favorites/{favoriteId}` - Remove a favorite

### Visited Places

- `POST /api/users/{userId}/visited` - Add a visited place
- `GET /api/users/{userId}/visited` - Get visited places for a user
- `DELETE /api/users/visited/{placeId}` - Remove a visited place

## Data Model

### User

- `id`
- `name`
- `email`
- `avatarUrl`
- `createdAt`

### Favorite

- `id`
- `userId`
- `destinationId`
- `addedAt`

### VisitedPlace

- `id`
- `userId`
- `placeName`
- `country`
- `visitedDate`
- `notes`

## Notes

- The app is configured as a discovery client via `@EnableDiscoveryClient`.
- Database tables are created/managed by JPA entities.
- The service expects external configuration for DB settings and service discovery if used in a full microservice environment.

## License

This project does not currently specify a license.
