package definitions;

import com.example.musicstreamingapi.MusicStreamingApiApplication;
import com.example.musicstreamingapi.model.User;
import com.example.musicstreamingapi.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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

import javax.validation.constraints.AssertTrue;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MusicStreamingApiApplication.class)
public class UserControllerTestDefs {
    private static final Logger logger = Logger.getLogger(PlaylistControllerTestDefs.class.getName());
    private static Response response;
    private String message;

    @LocalServerPort
    String port;

    @Autowired
    private UserService userService;

    private final static String BASE_URL = "http://localhost:";

    private static RequestSpecification request = RestAssured.given();


    /**
     * Retrieve a JSON Web Token (JWT) key by sending a POST request to the authentication endpoint
     * with a predefined user email and password.
     *
     * @return The JWT key obtained from the authentication response.
     * @throws JSONException If there are issues with JSON parsing while processing the response.
     */
    public String getJWTKey() throws JSONException {
        // Set the base URI and create a request
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        // Set the content-type header to indicate JSON data
        request.header("Content-Type", "application/json");

        String endpoint = BASE_URL + port + "/auth/users/login/";

        // Create a JSON request body with user email and password
        JSONObject requestBody = new JSONObject();
        requestBody.put("emailAddress", "suresh@g.com");
        requestBody.put("password", "suresh123");

        // Send a POST request to the authentication endpoint
        Response response = request.body(requestBody.toString()).post(endpoint);

        // Extract and return the JWT key from the authentication response
        return response.jsonPath().getString("jwt");
    }


    @When("I create an account")
    public void iCreateAnAccount() throws JSONException {
        String endpoint = BASE_URL + port + "/auth/users/register/";
        JSONObject requestBody = new JSONObject()
                .put("firstName", "Suresh")
                .put("lastName", "Sigera")
                .put("emailAddress", "suresh@g.com")
                .put("password", "suresh123");

        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post(endpoint);

        Assert.assertEquals(201, response.getStatusCode());
    }

    @Then("I get an account and user profile")
    public void iGetAnAccountAndUserProfile() throws JSONException {
        System.out.println(response.getBody().prettyPrint());
        System.out.println(getJWTKey());
    }


    @When("I delete the user")
    public void iDeleteTheUser() throws JSONException {
        int userId = response.jsonPath().getInt("data.id");
        String endpoint = BASE_URL + port + "/auth/users/" + userId + "/";
        System.out.println(endpoint);

        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + getJWTKey());
        response = request.delete(endpoint);

    }

    @Then("The user and user profile gets deleted")
    public void theUserAndUserProfileGetsDeleted() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("I update the user profile")
    public void iUpdateTheUserProfile() throws JSONException {
        int userId = response.jsonPath().getInt("data.id");
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + getJWTKey());
        requestBody.put("name", "Updated Name");
        response = request.body(requestBody.toString()).put(BASE_URL + port + "/auth/users/" + userId + "/");

    }

    @Then("The user profile is updated")
    public void theUserProfileIsUpdated() {
        Assert.assertEquals("Updated Name", response.jsonPath().getObject("data", User.class).getName());

    }
}