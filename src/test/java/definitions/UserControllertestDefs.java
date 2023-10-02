package definitions;

import com.example.musicstreamingapi.MusicStreamingApiApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MusicStreamingApiApplication.class)
public class UserControllertestDefs {

    private static final Logger logger = Logger.getLogger(PlaylistControllerTestDefs.class.getName());


}
