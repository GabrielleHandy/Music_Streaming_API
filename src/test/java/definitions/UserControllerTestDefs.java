package definitions;

import com.example.musicstreamingapi.MusicStreamingApiApplication;
import com.example.musicstreamingapi.model.User;
import com.example.musicstreamingapi.service.UserService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

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
    @Given("A list of Users")
    public void aListOfUsers() {

    }

    @When("I create an account.")
    public void iCreateAnAccount() {
        User newUser = new User();
        newUser.setName("John Doe");
        newUser.setEmailAddress("johndoe@example.com");
        newUser.setPassword("password123");

        createdUser = userService.createUser(newUser);
        assertNotNull(createdUser);
    }

    @Then("I get an account and user profile")
    public void iGetAnAccountAndUserProfile() {
    }
}
