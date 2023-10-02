package definitions;

import com.example.musicstreamingapi.MusicStreamingApiApplication;
import com.example.musicstreamingapi.model.User;
import com.example.musicstreamingapi.service.UserService;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.logging.Logger;

@CucumberContextConfiguration
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



}
