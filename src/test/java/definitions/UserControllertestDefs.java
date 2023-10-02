package definitions;

import com.example.musicstreamingapi.MusicStreamingApiApplication;
import com.example.musicstreamingapi.service.UserService;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MusicStreamingApiApplication.class)
public class UserControllertestDefs {

    private static final Logger logger = Logger.getLogger(PlaylistControllerTestDefs.class.getName());

    private static Response response;

    private static RequestSpecification request;

    private String token;
    private String message;

    @Autowired
    private UserService userService;

}
