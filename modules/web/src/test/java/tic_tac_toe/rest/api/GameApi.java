package tic_tac_toe.rest.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import tic_tac_toe.config.Urls.Game.GameFlow;
import tic_tac_toe.domain.move.MoveRequest;

import static io.restassured.RestAssured.given;

public abstract class GameApi {

    public static Response post(Long id, MoveRequest request) {
        return
                given().
                        contentType(ContentType.JSON).
                        body(request).
                        pathParam("id", id).
                when().
                        post(GameFlow.FULL);
    }

    public static MoveRequest createMoveRequest(Long id, int horizontalPosition, int verticalPosition) {
        return new MoveRequest()
                .setPlayerId(id)
                .setHorizontalPosition(horizontalPosition)
                .setVerticalPosition(verticalPosition);
    }
}
