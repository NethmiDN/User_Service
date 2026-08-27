# User Service

Spring Boot microservice for handling user profiles, preferences, favorites, and visited places in the My Memories distributed cloud architecture. It persists relational data in Google Cloud SQL (MySQL) and integrates with Spring Cloud Config and Eureka for centralized configuration and service discovery.

## Project Information

| Field | Details |
| :--- | :--- |
| Student Name | Nethmi Nanayakkara |
| Student ID | 241722047 |
| GCP Project ID | `nethmi-project` |
| Module | ITS 2130 - Enterprise Cloud Architecture |
| Service Role | User & Profile Management |

## Overview

This service exposes a REST API for:

- creating, updating, retrieving, and deleting user profiles
- storing favorite destinations per user
- tracking visited places per user
- serving user data to downstream services through a stable API boundary

It is built with Java 21 and Spring Boot 3.4.3, uses Spring Data JPA for persistence, and registers as a discovery client.

## Technology Stack

- Java 21
- Spring Boot 3.4.3
- Spring Web
- Spring Data JPA / Hibernate
- Spring Cloud Netflix Eureka Client
- Spring Cloud Config Client
- Validation
- Lombok
- Maven
- Google Cloud SQL (MySQL)

## API Endpoints

Base path: `/api/users`

| Method | Endpoint | Description | Request Payload | Response |
| :--- | :--- | :--- | :--- | :--- |
| `POST` | `/api/users` | Register a new user profile | `{ "name": "...", "email": "..." }` | `200 OK` (User JSON) |
| `GET` | `/api/users/{id}` | Fetch user profile details by ID | None | `200 OK` (User JSON) |
| `GET` | `/api/users` | Fetch all registered users | None | `200 OK` (Array of users) |
| `GET` | `/api/users/email/{email}` | Fetch a user by email | None | `200 OK` (User JSON) |
| `PUT` | `/api/users/{id}` | Update profile details | `{ "name": "...", "email": "..." }` | `200 OK` |
| `DELETE` | `/api/users/{id}` | Delete user account | None | `204 No Content` |
| `POST` | `/api/users/{userId}/favorites?destinationId=...` | Add a favorite destination | None | `200 OK` (Favorite JSON) |
| `GET` | `/api/users/{userId}/favorites` | Get all favorites for a user | None | `200 OK` (Array of favorites) |
| `DELETE` | `/api/users/favorites/{favoriteId}` | Remove a favorite | None | `200 OK` |
| `POST` | `/api/users/{userId}/visited` | Add a visited place | `{ "placeName": "...", "country": "...", "visitedDate": "YYYY-MM-DD", "notes": "..." }` | `200 OK` (VisitedPlace JSON) |
| `GET` | `/api/users/{userId}/visited` | Get visited places for a user | None | `200 OK` (Array of visited places) |
| `DELETE` | `/api/users/visited/{placeId}` | Remove a visited place | None | `200 OK` |
| `GET` | `/actuator/health` | Health check endpoint | None | `{"status":"UP"}` |

## Domain Model

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

## Local Setup

### Prerequisites

- JDK 21 or newer
- Apache Maven 3.8+ or the Maven wrapper
- MySQL database or Cloud SQL Auth Proxy
- Optional Spring Cloud Config Server at `http://localhost:8888`
- Optional Eureka Server for service discovery

### Configuration

The application loads `src/main/resources/application.yml`:

```yaml
spring:
  application:
    name: user-service
  config:
    import: optional:configserver:http://localhost:8888
```

The Config Server import is optional, so the service can still start without it when local overrides are provided.

### Run Locally

Windows:

```powershell
mvnw.cmd spring-boot:run
```

macOS/Linux:

```bash
./mvnw spring-boot:run
```

### Build

Windows:

```powershell
mvnw.cmd clean package
```

macOS/Linux:

```bash
./mvnw clean package
```

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
└── README.md
```

## Notes

- The service is configured as a discovery client.
- JPA entities are used to map relational data directly to Cloud SQL tables.
- The code currently returns `200 OK` for create and delete operations in the controller layer, so the API table above reflects the implemented behavior.

## License

This project does not currently specify a license.
