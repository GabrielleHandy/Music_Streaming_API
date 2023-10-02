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
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

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
        assertNotNull(createdUser);
        assertNotNull(createdUser.getUserProfile());


    }


//    @When("I login to the account")
//    public void iLoginToTheAccount() {
//
//    }
//
//    @Then("I get logged in and get a Jwt Token")
//    public void iGetLoggedInAndGetAJwtToken() {
//
//    }

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

        
    }

    @Then("The user profile is updated")
    public void theUserProfileIsUpdated() {
        
    }

    @When("I delete the user")
    public void iDeleteTheUser() {
        
    }

    @Then("The user and user profile gets deleted")
    public void theUserAndUserProfileGetsDeleted() {
    }
}
