package tic_tac_toe.rest;

import org.apache.http.HttpStatus;
import org.junit.Test;
import tic_tac_toe.domain.game.start.GameStartRequest;
import tic_tac_toe.rest.api.GameStartApi;
import tic_tac_toe.rest.tools.common.IntegrationTest;

public class GameStartControllerIT extends IntegrationTest {

    @Test
    public void createGameWithTwoNewPlayersShouldSuccess() {
        GameStartRequest firstPlayerGameStartRequest = GameStartApi.createFirstValidGameStartRequest("first");

        GameStartApi
                .post(firstPlayerGameStartRequest)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
