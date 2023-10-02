package definitions;

import com.example.musicstreamingapi.MusicStreamingApiApplication;
import com.example.musicstreamingapi.model.User;
import com.example.musicstreamingapi.service.UserService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MusicStreamingApiApplication.class)
public class UserControllerTestDefs {
    private static final Logger logger = Logger.getLogger(PlaylistControllerTestDefs.class.getName());
    private static Response response;
    private static RequestSpecification request;
    private String token;
    private String message;
    @LocalServerPort
    private String port;
    @Autowired
    private UserService userService;

    private User createdUser;
    private User updatedUser;
    private Long userId;
    private String loginEmailAddress;
    private String loginPassword;
    private static final String BASE_URL = "http://localhost:";
    public static String getJWTKey(String port) throws JSONException {
        // Set the base URI and create a request

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        // Set the content-type header to indicate JSON data
        request.header("Content-Type", "application/json");

        // Create a JSON request body with user email and password
        JSONObject requestBody = new JSONObject();
        requestBody.put("emailAddress", "johndoe@example.com");
        requestBody.put("password", "password123");

        // Send a POST request to the authentication endpoint
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/login/");

        // Extract and return the JWT key from the authentication response
        return response.jsonPath().getString("jwt");
    }


    @When("I create an account.")
    public void iCreateAnAccount() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("name","John Doe");
        requestBody.put("emailAddress","johndoe@example.com");
        requestBody.put("password","password123");
        request.header("Content-Type","application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/register/");

    }



    @Then("I get an account and user profile")
    public void iGetAnAccountAndUserProfile() {
        assertNotNull( response.jsonPath().getString("data"));

    }


    @When("I login to the account")
    public void iLoginToTheAccount() {



    }

    @Then("I get logged in and get a Jwt Token")
    public void iGetLoggedInAndGetAJwtToken() {

    }

    @When("I search for my account with an id")
    public void iSearchForMyAccountWithAnId() {
        userId = 1L;
    }

    @Then("I find an account")
    public void iFindAnAccount() {
        Optional<User> findUser = userService.getUserById(userId);
        Assert.assertTrue(findUser.isPresent());
        
    }

    @When("I update the user profile")
    public void iUpdateTheUserProfile() {
        userId = 1L;
        updatedUser = new User();
        updatedUser.setName("Updated Name");
        updatedUser.setEmailAddress("updated@example.com");
        updatedUser.setPassword("newpassword");


    }

    @Then("The user profile is updated")
    public void theUserProfileIsUpdated() {
        User updated = userService.updateUser(userId, updatedUser);
        Assert.assertNotNull(updated);
        Assert.assertEquals("Updated Name", updated.getName());
        Assert.assertEquals("updated@example.com", updated.getEmailAddress());
        
    }

    @When("I delete the user")
    public void iDeleteTheUser() {
        userId = 1L;
    }

    @Then("The user and user profile gets deleted")
    public void theUserAndUserProfileGetsDeleted() {
        userService.deleteUser(userId);
        Optional<User> deletedUser = userService.getUserById(userId);
        Assert.assertFalse(deletedUser.isPresent());
    }
}
