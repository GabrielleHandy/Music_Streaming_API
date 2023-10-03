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
import org.springframework.security.core.context.SecurityContextHolder;
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
    private static String token;
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

    public static void getJWTKey(String port) throws JSONException {
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
        token = response.jsonPath().getString("jwt");
    }


    @When("I create an account.")
    public void iCreateAnAccount() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "John Doe");
        requestBody.put("emailAddress", "johndoe@example.com");
        requestBody.put("password", "password123");
        request.header("Content-Type", "application/json");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/register/");

    }


    @Then("I get an account and user profile")
    public void iGetAnAccountAndUserProfile() {
        assertNotNull(response.jsonPath().getString("data"));
        createdUser = (response.jsonPath().getObject("data", User.class));

    }


    @When("I login to the account")
    public void iLoginToTheAccount() throws JSONException {
        getJWTKey(port);

    }

    @Then("I get logged in and get a Jwt Token")
    public void iGetLoggedInAndGetAJwtToken() {
        assertNotNull(token);
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
    public void iUpdateTheUserProfile() throws JSONException {

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + token);
        requestBody.put("name", "Updated Name");
        response = request.body(requestBody.toString()).put(BASE_URL + port + "/auth/users/" + createdUser.getId() + "/");

    }

    @Then("The user profile is updated")
    public void theUserProfileIsUpdated() {
        Assert.assertEquals("Updated Name", response.jsonPath().getObject("data", User.class).getName());
    }

    @When("I delete the user")
    public void iDeleteTheUser() throws JSONException {

        System.out.println(token);
//        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        request.header("Content-Type","application/json");
        request.header("Authorization","Bearer "+ token);

        response = request.delete(BASE_URL + port + "/auth/users/"+ createdUser.getId()+"/");
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

    }

    @Then("The user and user profile gets deleted")
    public void theUserAndUserProfileGetsDeleted() {
//       Assert.assertSame(createdUser,response.jsonPath().getObject("data", User.class));
        System.out.println(response.toString());
        System.out.println(Optional.ofNullable(response.jsonPath().get()));
Assert.assertEquals(createdUser.getId(),response.jsonPath().getObject("data", User.class).getId());
    }
}
