package definitions;

import com.example.musicstreamingapi.MusicStreamingApiApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MusicStreamingApiApplication.class)
public class PlaylistControllerTestDefs {
    private static final String BASE_URL = "http://localhost:";

    private Response response;



    @LocalServerPort
    private static String port;
    /**
     * Retrieves a JWT (JSON Web Token) key by sending a POST request to the authentication endpoint.
     * This method sets up the necessary HTTP request with user credentials and sends it to the
     * authentication endpoint to obtain a JWT key for user authentication.
     *
     * @return The JWT key (token) received from the authentication response.
     * @throws JSONException If there is an issue with JSON processing.
     */

    public static String getJWTKey() throws JSONException {
        // Set the base URI and create a request
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        // Set the content-type header to indicate JSON data
        request.header("Content-Type", "application/json");

        // Create a JSON request body with user email and password
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "suresh@ga.com");
        requestBody.put("password", "password");

        // Send a POST request to the authentication endpoint
        Response response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/auth/users/login");

        // Extract and return the JWT key from the authentication response
        return response.jsonPath().getString("jwt");
    }

    @Given("A list of playlists are available")
    public void aListOfPlaylistsAreAvailable() {
        try {
            ResponseEntity<String> response = new RestTemplate().exchange(BASE_URL + port + "/api/playlists/", HttpMethod.GET, null, String.class);
            List<Map<String, String>> playlists = JsonPath.from(String.valueOf(response.getBody())).get("data");
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
            Assert.assertFalse(playlists.isEmpty());
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }

    }

//    @When("I create a playlist")
//    public void iCreateAPlaylist() {
//    }
//
//    @Then("The playlist is created")
//    public void thePlaylistIsCreated() {
//    }
//
//    @When("I remove playlist from my list of playlists")
//    public void iRemovePlaylistFromMyListOfPlaylists() {
//    }
//
//    @When("I update a playlist")
//    public void iUpdateAPlaylist() {
//    }
//
//    @Then("The playlist is updated")
//    public void thePlaylistIsUpdated() {
//    }
//
//    @Given("A list of songs are available in a playlist")
//    public void aListOfSongsAreAvailableInAPlaylist() {
//    }
//
//    @When("I add a song to the playlist")
//    public void iAddASongToThePlaylist() {
//    }
//
//    @Then("The song is added to playlist")
//    public void theSongIsAddedToPlaylist() {
//    }
//
//    @When("I remove a song from playlist")
//    public void iRemoveASongFromPlaylist() {
//    }
//
//    @Then("The song is removed")
//    public void theSongIsRemoved() {
//    }
//
//    @When("I update a playlist name")
//    public void iUpdateAPlaylistName() {
//    }
//
//    @Then("The playlist name is updated")
//    public void thePlaylistNameIsUpdated() {
//    }
}
