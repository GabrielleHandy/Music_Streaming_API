# Music Streaming API
***
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
![ERD]()

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

<Planning>

</Planning>

