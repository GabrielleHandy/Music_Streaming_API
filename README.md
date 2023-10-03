# Unit 2 Project: Monolithic Backend with Spring Boot
## Description


This project is focused on developing a monolithic backend using Spring Boot. It integrates key modules like Spring Security and JWT tokens, while also implementing CRUD (Create, Read, Update, Delete) operations. The application utilizes an in-memory H2 database and runs on the Tomcat server.

## Development Approach

- Incorporating driver and navigator roles when debugging and solving problems.
- Follow the KISS and DRY principles.
- Use Git branches for project development.
- Conform to the MVC design pattern with separate controllers and services.
- Implement TDD using mockMvc for controller unit tests and Cucumber with Rest Assured for service class testing, for each endpoints.
- Documented each method that using doc strings and inline comments.



# Music Streaming API
# Dependencies

- Spring Boot Starter Dependencies:
    - `spring-boot-starter-data-jpa`
    - `spring-boot-starter-security`
    - `spring-boot-starter-web`
    - `spring-boot-starter-web-services`

- Spring Boot Devtools (Runtime):
    - `spring-boot-devtools`

- H2 Database (Runtime):
    - `com.h2database:h2`

- Spring Boot Test Dependencies:
    - `spring-boot-starter-test`
    - `spring-security-test`

- JSON Web Token (JWT) Dependencies:
    - `io.jsonwebtoken:jjwt-api:0.11.5`
    - `io.jsonwebtoken:jjwt-impl:0.11.5` (Runtime)
    - `io.jsonwebtoken:jjwt-jackson:0.11.5` (Runtime)

- Testing Dependencies:
    - `junit:junit:4.12` (for JUnit 4)
    - `org.mockito:mockito-core:4.10.0`
    - `org.mockito:mockito-junit-jupiter:4.5.1`
    - `org.hamcrest:hamcrest:2.2`
    - `org.mockito:mockito-inline:4.10.0`
    - `org.junit.jupiter:junit-jupiter-api:5.10.0` (for JUnit 5)
