package tic_tac_toe.rest;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tic_tac_toe.domain.game.start.GameStartRequest;
import tic_tac_toe.domain.game.start.GameStartResponse;
import tic_tac_toe.domain.player.PlayerDto;
import tic_tac_toe.entity.Game;
import tic_tac_toe.entity.Player;
import tic_tac_toe.mapper.PlayerMapper;
import tic_tac_toe.rest.api.GameStartApi;
import tic_tac_toe.rest.data.spec.GameStartResponseSpec;
import tic_tac_toe.rest.tools.GameHelper;
import tic_tac_toe.rest.tools.PlayerHelper;
import tic_tac_toe.rest.tools.common.Fields.GameStart;
import tic_tac_toe.rest.tools.common.IntegrationTest;

public class GameStartControllerIT extends IntegrationTest {
    @Autowired private PlayerMapper mapper;

    @Test
    public void createGameWithNewPlayerShouldSuccess() {
        GameStartRequest firstPlayerGameStartRequest = GameStartApi.createFirstValidGameStartRequest("first");

        GameStartResponse expectedResponse = new GameStartResponse()
                .setCrossPlayer(new PlayerDto().setLogin(firstPlayerGameStartRequest.getLogin()))
                .setStarted(false);

        GameStartApi
                .post(firstPlayerGameStartRequest)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .spec(GameStartResponseSpec.of(expectedResponse).withoutRoot());
    }

    @Test
    public void createGameWithNaughtPlayerShouldSuccess() {
        Player crossPlayer = PlayerHelper.createOne();

        Game game = GameHelper.createOne()
                .setCrossPlayer(crossPlayer);

        data.game(game).build();

        crossPlayer = game.getCrossPlayer();

        GameStartRequest secondPlayer = GameStartApi.createSecondValidGameStartRequest("second", game.getId());

        GameStartResponse expectedResponse = new GameStartResponse()
                .setCrossPlayer(mapper.toDto(crossPlayer))
                        .setGameId(game.getId())
                .setNaughtPlayer(new PlayerDto()
                        .setLogin(secondPlayer.getLogin()))
                .setStarted(true);


        GameStartApi
                .post(secondPlayer)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .spec(GameStartResponseSpec.of(expectedResponse).withoutRoot());
    }

    @Test
    public void createGameWithCrossAndNaughtPlayersShouldSuccess() {
        Player crossPlayer = PlayerHelper.createOne();

        GameStartRequest request = GameStartApi.createFirstValidGameStartRequest(crossPlayer.getLogin());

        GameStartResponse expectedResponse = new GameStartResponse()
                .setStarted(false)
                .setNaughtPlayer(null)
                .setCrossPlayer(mapper.toDto(crossPlayer));

        Integer gameId = GameStartApi
                .post(request)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .spec(GameStartResponseSpec.of(expectedResponse).withoutRoot())
                .extract()
                    .path(GameStart.GAME_ID);

        Player naughtPlayer = PlayerHelper.createOne();

        GameStartRequest secondRequest = GameStartApi
                .createSecondValidGameStartRequest(naughtPlayer.getLogin(), gameId.longValue());

        expectedResponse = new GameStartResponse()
                .setCrossPlayer(mapper.toDto(crossPlayer))
                .setGameId(gameId.longValue())
                .setNaughtPlayer(mapper.toDto(naughtPlayer))
                .setStarted(true);

        GameStartApi
                .post(secondRequest)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .spec(GameStartResponseSpec.of(expectedResponse).withoutRoot());
    }

    @Test
    public void createNewGameWithAlreadyExistingPlayers() {
        Player crossPlayer = PlayerHelper.createOne();
        Player naughtPlayer = PlayerHelper.createOne();

        data.player(crossPlayer).build();
        data.player(naughtPlayer).build();

        GameStartRequest request = GameStartApi.createFirstValidGameStartRequest(crossPlayer.getLogin());

        GameStartResponse expectedResponse = new GameStartResponse()
                .setStarted(false)
                .setNaughtPlayer(null)
                .setCrossPlayer(mapper.toDto(crossPlayer));

        Integer gameId = GameStartApi
                .post(request)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .spec(GameStartResponseSpec.of(expectedResponse).withoutRoot())
                .extract()
                .path(GameStart.GAME_ID);

        GameStartRequest secondRequest = GameStartApi
                .createSecondValidGameStartRequest(naughtPlayer.getLogin(), gameId.longValue());

        expectedResponse = new GameStartResponse()
                .setCrossPlayer(mapper.toDto(crossPlayer))
                .setGameId(gameId.longValue())
                .setNaughtPlayer(mapper.toDto(naughtPlayer))
                .setStarted(true);

        GameStartApi
                .post(secondRequest)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .spec(GameStartResponseSpec.of(expectedResponse).withoutRoot());
    }

    @Test
    public void createGameWithTwoEqualsPlayersShouldFail() {
        Player crossPlayer = PlayerHelper.createOne();

        Game game = GameHelper.createOne()
                .setCrossPlayer(crossPlayer);

        data.game(game).build();

        crossPlayer = game.getCrossPlayer();

        GameStartRequest secondPlayer = GameStartApi
                .createSecondValidGameStartRequest(crossPlayer.getLogin(), game.getId());


        GameStartApi
                .post(secondPlayer)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void createGameWithNonExistingGameIdShouldFail() {

        GameStartRequest secondPlayer = GameStartApi.createSecondValidGameStartRequest("second", -33L);

        GameStartApi
                .post(secondPlayer)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void createGameWithEmptyLoginCrossPlayerShouldFail() {
        GameStartRequest crossPlayer = GameStartApi.createFirstValidGameStartRequest("");

        GameStartApi
                .post(crossPlayer)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
