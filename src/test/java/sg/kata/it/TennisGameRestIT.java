package sg.kata.it;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sg.kata.infrastructure.services.TennisGameService;

import javax.servlet.ServletContext;

import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Created by Nassim MOUALEK on 31/08/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class TennisGameRestIT {

    @LocalServerPort
    protected int port;
    @Autowired
    protected ServletContext servletContext;
    @Autowired
    protected TennisGameService tennisGameService;

    private static String player1 = "player1";
    private static String player2 = "player2";

    @Before
    public void setup() {
        RestAssured.port = port;
        basePath = servletContext.getContextPath();
        tennisGameService.clearPlayers();
    }

    @Test
    public void testJoinTheGame() {
        testJoinTheGame(player1);
    }

    public void testJoinTheGame(String name) {

        given()
                .when()
                .get("/joinTheGame/{player}", name)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("players['" + name + "'].name", is(name));

    }

    @Test
    public void testWinPoint() {
        testJoinTheGame(player1);
        testJoinTheGame(player2);
        given()
                .when()
                .get("/winPoint/{player}", player1)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("players['" + player1 + "'].gameScore.score", is(15));

    }
}
