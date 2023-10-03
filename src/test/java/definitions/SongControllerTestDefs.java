package definitions;

import com.example.musicstreamingapi.MusicStreamingApiApplication;
import com.example.musicstreamingapi.model.Song;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MusicStreamingApiApplication.class)
public class SongControllerTestDefs {

    private static final String BASE_URL = "http://localhost:";

    // Logger initialization
    private static final Logger log = getLogger(SongControllerTestDefs.class.getName());

    @LocalServerPort
    String port;

    private static Response response;
    // Verify availability of songs in a genre

    @Given("A list of songs are available in genre")
    public void aListOfSongsAreAvailable() {
        log.info("Calling aListOfSongsAreAvailable");
        try {
            RestAssured.baseURI = BASE_URL;
            RequestSpecification request = RestAssured.given();
            request.header("Authorization", "Bearer " + getJWTKey(port));
            request.header("Content-Type", "application/json");
            // Send a GET request to retrieve the list of songs
            response = request.get(BASE_URL + port + "/api/songs/");
            log.info(request.toString());
            List<Song> songs = response.jsonPath().get("data");
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.value()); //status is OK (200)
            Assert.assertTrue(songs.size() > 0); //list of songs is not empty
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @When("The user requests to get songs by genre ID")
    public void theUserRequestsToGetSongsByGenreID() {
        try {
            RestAssured.baseURI = BASE_URL;
            RequestSpecification request = RestAssured.given();
            request.header("Authorization", "Bearer " + getJWTKey(port));
            request.header("Content-Type", "application/json");

            Long genreId = 1L; //testing

            log.info("Requesting songs for Genre ID: " + genreId);

            response = request.get(BASE_URL + port + "/api/songs/Genre/" + genreId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Then("The it should return a list of songs by genre ID")
    public void theItShouldReturnAListOfSongsByGenreID() {
        try {
            Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
            List<Song> songs = response.jsonPath().getList("data", Song.class);
            Assert.assertNotNull(songs);
            Assert.assertTrue(songs.size() > 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @When("The user requests to get a list of songs")
    public void theUserRequestsToGetAListOfSongs() {
        try {
            RestAssured.baseURI = BASE_URL;
            RequestSpecification request = RestAssured.given();
            request.header("Authorization", "Bearer " + getJWTKey(port));
            request.header("Content-Type", "application/json");

            log.info("Requesting a list of songs");

            response = request.get(BASE_URL + port + "/api/songs/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("The system should respond with a list of songs")
    public void theSystemShouldRespondWithAListOfSongs() {
        try {
            Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
            List<Song> songs = response.jsonPath().getList("data", Song.class);
            Assert.assertNotNull(songs);
            Assert.assertTrue(songs.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve JWT token for SongController tests
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

}