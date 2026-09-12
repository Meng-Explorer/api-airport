# Airport Backend

Spring Boot backend for airport operations and ticket booking workflows.

## Technology Stack

- Java 21
- Spring Boot 3.3.0
- Spring Web
- Spring Data JPA / Hibernate
- PostgreSQL
- Spring Security with JWT authentication
- Maven

## Features

The API provides backend services for:

- Airports, airlines, terminals, gates, and flights
- Flight assignments and staff scheduling
- Staff authentication and management
- Sitting and shift management
- Operational logs and alerts
- Reports and analyst views

## Prerequisites

- JDK 21 or newer
- PostgreSQL
- A database named `ticketbooking_db` (or an equivalent database configured in `application.properties`)

## Configuration

Update `src/main/resources/application.properties` with the connection details for your local PostgreSQL instance before starting the application.

For shared or deployed environments, move database credentials and the JWT secret to environment variables or another secret-management solution. Do not commit real credentials or signing keys to source control.

The application starts on port `9181` by default.

## Running Locally

From the project root, use the Maven wrapper:

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Once started, the API is available at `http://localhost:9181`.

## Testing

Run the test suite with:

```powershell
.\mvnw.cmd test
```

## Project Structure

```text
src/main/java/TicketBooking/com/
├── config/       Application, CORS, web, and security configuration
├── controller/   REST API controllers
├── dto/          Request and response objects
├── enums/        Domain enumerations
├── exception/    Application exceptions and global error handling
├── models/       JPA entities
├── repository/   Spring Data repositories
├── security/     Authentication and JWT components
└── service/      Business logic
```

## License

No license has been specified for this repository.
