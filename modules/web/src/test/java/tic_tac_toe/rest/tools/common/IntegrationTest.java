package tic_tac_toe.rest.tools.common;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import tic_tac_toe.rest.data.tools.DataProvider;
import tic_tac_toe.rest.tools.IntTestsProfilesResolver;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "it", resolver = IntTestsProfilesResolver.class)
public abstract class IntegrationTest {

    @LocalServerPort
    private int serverPort;

    @Autowired
    protected DataProvider data;

    @Before
    public void beforeEachTest() {
        initRestAssured();
    }

    @After
    public void afterTest() {
        data.clear();
    }

    private void initRestAssured() {
        RestAssured.port = serverPort;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
