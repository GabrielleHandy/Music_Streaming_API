package definitions;

import io.restassured.response.Response;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.logging.Logger;

public class SongControllerTestDefs {

    private static final String Base_URL = "http://localhost:";

    //Logger initialization
    private static final Logger log = Logger.getLogger(SongControllerTestDefs.class.getName());

    @LocalServerPort
    String port;

    private static Response response;
    public SongControllerTestDefs() {
        Given("^A list of songs are available in genre"), () -> {
        });
    }
}
