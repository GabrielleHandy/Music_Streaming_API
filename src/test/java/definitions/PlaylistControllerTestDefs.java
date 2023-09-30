package definitions;

import com.example.musicstreamingapi.MusicStreamingApiApplication;
import com.example.musicstreamingapi.model.Playlist;
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
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MusicStreamingApiApplication.class)
public class PlaylistControllerTestDefs {

    private static final Logger logger = Logger.getLogger(PlaylistControllerTestDefs.class.getName());
    private static final String BASE_URL = "http://localhost:";

    private static Response response;


    private String token;
    private String message;

    @LocalServerPort
    private String port;
    /**
     * Retrieves a JWT (JSON Web Token) key by sending a POST request to the authentication endpoint.
     * This method sets up the necessary HTTP request with user credentials and sends it to the
     * authentication endpoint to obtain a JWT key for user authentication.
     *
     * @return The JWT key (token) received from the authentication response.
     * @throws JSONException If there is an issue with JSON processing.
     */

    public static String getJWTKey(String port) throws JSONException {
        // Set the base URI and create a request

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        // Set the content-type header to indicate JSON data
        request.header("Content-Type", "application/json");

        // Create a JSON request body with user email and password
        JSONObject requestBody = new JSONObject();
        requestBody.put("emailAddress", "suresh@ga.com");
        requestBody.put("password", "suresh123");

        // Send a POST request to the authentication endpoint
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/login/");

        // Extract and return the JWT key from the authentication response
        return response.jsonPath().getString("jwt");
    }

    public RequestSpecification createRequest(){
        try{
            if(token == null){

                token = getJWTKey(port);
            }


            RestAssured.baseURI = BASE_URL;
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type", "application/json");
            request.header("Authorization", "Bearer " + token);
            return request;

        }catch(JSONException e){
            logger.info("Json issue " + e.getMessage());
            return null;
        }

    }

    @Given("A list of playlists are available")
    public void aListOfPlaylistsAreAvailable() {
            RequestSpecification request = createRequest();
            response = request.get(BASE_URL+ port +"/api/playlists/");

            List<Playlist> playlists = response.jsonPath().get("data");
            message = response.jsonPath().get("message");
            Assert.assertEquals(HttpStatus.OK.value(),response.getStatusCode());
            Assert.assertEquals("Success", message);
            Assert.assertFalse(playlists.isEmpty());

        }



    @When("I create a playlist")
    public void iCreateAPlaylist() {

        try {
            RequestSpecification request = createRequest();
            JSONObject requestBody = new JSONObject();
            requestBody.put("name", "Party Mix");

            response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/playlists/");
        }catch(JSONException e){
            logger.info("Json issue " + e.getMessage());
        }
    }

    @Then("The playlist is created")
    public void thePlaylistIsCreated() {
        message = response.jsonPath().get("message");
        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
        Assert.assertEquals("Successfully created playlist named Party Mix", message);
    }

    @When("I remove playlist from my list of playlists")
    public void iRemovePlaylistFromMyListOfPlaylists() {
        Playlist deletePlaylist = response.jsonPath().getObject("data", Playlist.class);
        logger.info(deletePlaylist.toString());
        RequestSpecification request = createRequest();
        response = request.delete(BASE_URL + port + MessageFormat.format("/api/playlists/{0}/", deletePlaylist.getId()));

    }

    @Then("The playlist is removed")
    public void thePlaylistIsRemoved() {
        message = response.jsonPath().get("message");
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        Assert.assertEquals("Successfully deleted playlist named Party Mix", message);
    }


//
//    @When("I update a playlist")
//    public void iUpdateAPlaylist() {
//    }
//
//    @Then("The playlist is updated")+-
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
