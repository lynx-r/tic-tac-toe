package tic_tac_toe.rest.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import tic_tac_toe.config.Urls.Game.GameStart;
import tic_tac_toe.domain.game.start.GameStartRequest;

import static io.restassured.RestAssured.given;

public abstract class GameStartApi {

    public static Response post(GameStartRequest request) {
        return
                given().
                        contentType(ContentType.JSON).
                        body(request).
                when().
                        post(GameStart.PART);
    }

    public static GameStartRequest createFirstValidGameStartRequest(String login) {
        return
                new GameStartRequest()
                        .setLogin(login);
    }

    public static GameStartRequest createSecondValidGameStartRequest(String login, Long gameId) {
        return
                new GameStartRequest()
                        .setLogin(login)
                        .setGameId(gameId);
    }
}
