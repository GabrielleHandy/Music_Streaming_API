# Music Streaming API: Monolithic Backend with Spring Boot
## Description


This project is focused on developing a Music Streaming API that implements user profiles, music categorization, and personalized playlists. It is a monolithic backend that uses Spring Boot Application. In addition, it integrates key modules like Spring Security and JWT tokens, while also implementing CRUD (Create, Read, Update, Delete) operations. The application utilizes an in-memory H2 database and runs on the Tomcat server. Lastly, this project uses Open API to document endpoints.

## Development Approach

- Incorporating driver and navigator roles when debugging and solving problems.
- Follow the KISS and DRY principles.
- Use Git branches for project development.
- Conform to the MVC design pattern with separate controllers and services.
- Implement TDD using mockMvc for controller unit tests and Cucumber with Rest Assured for service class testing, for each endpoints.
- Documented each method using doc strings and inline comments.
### Challenges 
***
- A challenge we faced was implementing Cucumber tests. We had to work together using driver navigator and code reviews to  get better at testing our endpoints.
- One of our challenges was learning how to merge and create branches, we overcame this by coming up with a merge plan that helped to avoid merge conflicts and made sure that when we pushed to main all the code was double-checked and okayed by everyone in the group.
- Another challenge was using MockMVC to test service classes, we overcame this by working together using driver/navigator and through code reviews.
- While following the TDD testing, there were some challenges while trying to test for controllers. Hence, taking a different approach of testing was needed, so instead of utilizing mockMVC we decided to implement Cucumber testing.

# User Stories

As a User, I want to be able to create my own playlists so I can curate my own collection of music.


As a User I want to be able to search for songs by title, artist, or genre, so I can easily find the music I want to listen to.



As a User, I want to have a user profile that I can securely login to where I can view my  playlists.



As a User, I want to see information about the currently playing song, including its title, and artist.



As a User, I want to be able to edit my playlist by adding and removing songs.


# Dependencies
***
### Spring Boot Dependencies:

- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- Spring Boot Starter Web
- Spring Boot Starter Web Services
- Spring Boot DevTools (scope: runtime, optional: true)
- Spring Boot Starter Test (scope: test)
- Spring Security Test (scope: test)
- Spring Boot Starter OAuth2 Resource Server
### Testing Dependencies:

- JUnit: 4.12 (scope: test)
- Cucumber Java: 6.8.1 (scope: test)
- Cucumber JUnit: 6.8.1 (scope: test)
- Cucumber Spring: 6.8.1 (scope: test)
- JUnit Jupiter API: 5.10.0 (scope: test)
- Mockito JUnit Jupiter: 4.5.1 (scope: test)
- Hamcrest: 2.2 (scope: test)
- Mockito Inline: 4.10.0 (scope: test)
- Rest Assured (scope: test, excluding groovy-xml)
### Database Dependencies:

- H2 Database (scope: runtime)
### JWT (JSON Web Token) Dependencies:

- jjwt-api: 0.11.5
- jjwt-impl: 0.11.5 (scope: runtime)
- jjwt-jackson: 0.11.5 (scope: runtime)
### Documentation and UI:

- Springdoc OpenAPI UI: 1.6.12

## Entity Relationship Diagram (ERD)
***
![ERD](src/main/resources/static/ERD image.png)

## API Endpoints
***
<details>
  <summary> <b>User Endpoints</b></summary>

| HTTP Methods | Endpoint URL                         | Functionality           | Access    | 
|--------------|--------------------------------------|-------------------------|-----------|
| POST         | `/auth/users/register/`              | Register a new user     | public    |
| POST         | `/auth/users/login/`                 | Login a registered user | public    |
| PUT          | `/auth/users/{userId}`                       | Update a User           | private   |
| GET          | `/auth/users/{userId}`                       | Get a User by Id        | private   |
| DELETE       | `/auth/users/{userId}/`               | Delete a User           | private   |

</details>

<details>
  <summary> <b>Song Endpoints</b></summary>

| HTTP Methods | Endpoint URL                         | Functionality      | Access  | 
|--------------|--------------------------------------|--------------------|---------|
| GET          | `/api/songs`              | Get all songs      | private |
| GET          | `/api/songs/{songId}`                 | Get a song by Id   | private |
| GET          | `/api/songs/Genre/{genreId}`                       | Get songs by Genre | private |

</details>
<details>
  <summary> <b>Playlist Endpoints</b></summary>

| HTTP Methods | Endpoint URL                         | Functionality             | Access  | 
|--------------|--------------------------------------|---------------------------|---------|
| GET          | `/api/playlists/`              | Retrieve a list of all playlists.           | private |
| GET          | `/api/playlists/{playlistId}/`                 | Retrieve a playlist by Id | private |
| GET          | `/api/playlists/{playlistId}/songs/`                       | Retrieve all songs in a playlist by ID.      | private |
| PUT          | `/api/playlists/{playlistId}/`              | Update an existing playlist by ID.          | private |
| POST         | `/api/playlists/`                 | Create a new playlist.        | private |
| POST         | `/api/playlists/{playlistId}/songs/{songId}/`                       | Add a song to a playlist by specifying both playlist and song IDs.        | private |
| DELETE       | `/api/playlists/{playlistId}/`                 | Delete an existing playlist by ID.         | private |
| DELETE       | `/api/playlists/{playlistId}/songs/{songId}/`                       | Remove a song from a playlist by specifying both playlist and song IDs.        | private |

</details>

### Acknowledgment:
