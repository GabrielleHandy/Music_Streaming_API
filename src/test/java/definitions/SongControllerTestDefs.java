package definitions;

import com.example.musicstreamingapi.MusicStreamingApiApplication;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
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
            // Send a GET request to retrieve the list of songs
            ResponseEntity<String> response = new RestTemplate().exchange(BASE_URL + port + "/api/songs/", HttpMethod.GET, null, String.class);
            List<Map<String, String>> songs = JsonPath.from(String.valueOf(response.getBody())).get("data");
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK); //status is OK (200)
            Assert.assertTrue(songs.size() > 0); //list of songs is not empty
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }
}
