package tic_tac_toe.rest;

import io.restassured.response.ValidatableResponse;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpStatus;
import org.junit.Test;
import tic_tac_toe.domain.field.CellDto;
import tic_tac_toe.domain.field.FieldResponse;
import tic_tac_toe.domain.game.GameStatusResponse;
import tic_tac_toe.domain.game.start.GameStartRequest;
import tic_tac_toe.domain.game.start.GameStartResponse;
import tic_tac_toe.domain.move.MoveRequest;
import tic_tac_toe.domain.move.MoveResponse;
import tic_tac_toe.domain.move.MoveResponse.MoveStatus;
import tic_tac_toe.domain.player.PlayerDto;
import tic_tac_toe.enums.GameResult;
import tic_tac_toe.enums.GameSymbol;
import tic_tac_toe.rest.api.GameApi;
import tic_tac_toe.rest.api.GameStartApi;
import tic_tac_toe.rest.data.spec.FieldResponseSpec;
import tic_tac_toe.rest.data.spec.GameStartResponseSpec;
import tic_tac_toe.rest.data.spec.GameStatusResponseSpec;
import tic_tac_toe.rest.data.spec.MoveResponseSpec;
import tic_tac_toe.rest.tools.common.Fields;
import tic_tac_toe.rest.tools.common.Fields.GameStart;
import tic_tac_toe.rest.tools.common.IntegrationTest;

@SuppressWarnings(value = "MethodLength")
public class WorkflowIT extends IntegrationTest {

    /**
     *  | |       |o|     x|o|     x|o|     x|o|     x|o|o     x|o|o
     *  |x|  ->   |x|  ->  |x|  ->  |x|  ->  |x|  ->  |x|  ->  x|x|
     *  | |       | |      | |      | |o    x| |o    x| |o     x| |o
     *
     *  CROSS WON
     */
    @Test
    public void checkCrossWin() {
        GameStartRequest request = GameStartApi.createFirstValidGameStartRequest("firstPlayer");

        GameStartResponse expectedResponse = new GameStartResponse()
                .setCrossPlayer(new PlayerDto().setLogin(request.getLogin()))
                .setStarted(false);

        Integer gameId = GameStartApi
                .post(request)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .spec(GameStartResponseSpec.of(expectedResponse).withoutRoot())
                .extract()
                .path(GameStart.GAME_ID);

        request = GameStartApi.createSecondValidGameStartRequest("secondPlayer", gameId.longValue());

        expectedResponse.setNaughtPlayer(new PlayerDto().setLogin(request.getLogin())).setStarted(true);

        ValidatableResponse response = GameStartApi
                .post(request)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .spec(GameStartResponseSpec.of(expectedResponse).withoutRoot());

        int crossPlayerId = response.extract().path(GameStart.CROSS_PLAYER + '.' + Fields.Player.ID);
        int naughtPlayerId = response.extract().path(GameStart.NAUGHT_PLAYER + '.' + Fields.Player.ID);

        List<CellDto> cells = new ArrayList<>();
        FieldResponse fieldResponse = new FieldResponse().setCells(cells);

        GameApi.
                getField(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(FieldResponseSpec.of(fieldResponse).withoutRoot());

        MoveRequest moveRequest = GameApi.createMoveRequest((long) crossPlayerId, 2, 2);

        MoveResponse moveResponse = new MoveResponse()
                .setStatus(MoveStatus.SUCCESS)
                .setGameFinished(false);

        GameApi.
                post(gameId.longValue(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(moveResponse).withoutRoot());

        cells.add(new CellDto().setSymbol(GameSymbol.CROSS).setHorizontalPosition(2).setVerticalPosition(2));

        GameApi.
                getField(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(FieldResponseSpec.of(fieldResponse).withoutRoot());

        moveRequest = GameApi.createMoveRequest((long) naughtPlayerId, 1, 2);

        GameApi.
                post(gameId.longValue(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(moveResponse).withoutRoot());

        cells.add(new CellDto().setSymbol(GameSymbol.NAUGHT).setHorizontalPosition(1).setVerticalPosition(2));

        GameApi.
                getField(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(FieldResponseSpec.of(fieldResponse).withoutRoot());

        moveRequest = GameApi.createMoveRequest((long) crossPlayerId, 1, 1);

        GameApi.
                post(gameId.longValue(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(moveResponse).withoutRoot());

        cells.add(new CellDto().setSymbol(GameSymbol.CROSS).setHorizontalPosition(1).setVerticalPosition(1));

        GameApi.
                getField(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(FieldResponseSpec.of(fieldResponse).withoutRoot());

        moveRequest = GameApi.createMoveRequest((long) naughtPlayerId, 3, 3);

        GameApi.
                post(gameId.longValue(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(moveResponse).withoutRoot());

        cells.add(new CellDto().setSymbol(GameSymbol.NAUGHT).setHorizontalPosition(3).setVerticalPosition(3));

        GameApi.
                getField(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(FieldResponseSpec.of(fieldResponse).withoutRoot());

        moveRequest = GameApi.createMoveRequest((long) crossPlayerId, 3, 1);

        GameApi.
                post(gameId.longValue(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(moveResponse).withoutRoot());

        cells.add(new CellDto().setSymbol(GameSymbol.CROSS).setHorizontalPosition(3).setVerticalPosition(1));

        GameApi.
                getField(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(FieldResponseSpec.of(fieldResponse).withoutRoot());

        moveRequest = GameApi.createMoveRequest((long) naughtPlayerId, 1, 3);

        GameApi.
                post(gameId.longValue(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(moveResponse).withoutRoot());

        cells.add(new CellDto().setSymbol(GameSymbol.NAUGHT).setHorizontalPosition(1).setVerticalPosition(3));

        GameApi.
                getField(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(FieldResponseSpec.of(fieldResponse).withoutRoot());

        moveRequest = GameApi.createMoveRequest((long) crossPlayerId, 2, 1);

        moveResponse.setGameFinished(true);

        GameApi.
                post(gameId.longValue(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(moveResponse).withoutRoot());

        cells.add(new CellDto().setSymbol(GameSymbol.CROSS).setHorizontalPosition(2).setVerticalPosition(1));

        GameApi.
                getField(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(FieldResponseSpec.of(fieldResponse).withoutRoot());

        GameStatusResponse gameStatusResponse = new GameStatusResponse().setResult(GameResult.CROSS_WON);

        GameApi.
                getStatus(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(GameStatusResponseSpec.of(gameStatusResponse).withoutRoot());
    }

    /**
     *  | |      o| |     o| |x    o| |x    o| |x    o| |x     o| |x      o|o|x      o|o|x
     *  |x|  ->   |x|  ->  |x|  ->  |x|  -> x|x|  -> x|x|o ->  x|x|o  ->  x|x|o  ->  x|x|o
     *  | |       | |      | |     o| |     o| |     o| |      o|x|       o|x|       o|x|x
     *
     *  DRAW
     */
    @Test
    public void checkDraw() {
        GameStartRequest request = GameStartApi.createFirstValidGameStartRequest("firstPlayer");

        GameStartResponse expectedResponse = new GameStartResponse()
                .setCrossPlayer(new PlayerDto().setLogin(request.getLogin()))
                .setStarted(false);

        Integer gameId = GameStartApi
                .post(request)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .spec(GameStartResponseSpec.of(expectedResponse).withoutRoot())
                .extract()
                .path(GameStart.GAME_ID);

        request = GameStartApi.createSecondValidGameStartRequest("secondPlayer", gameId.longValue());

        expectedResponse.setNaughtPlayer(new PlayerDto().setLogin(request.getLogin())).setStarted(true);

        ValidatableResponse response = GameStartApi
                .post(request)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .spec(GameStartResponseSpec.of(expectedResponse).withoutRoot());

        int crossPlayerId = response.extract().path(GameStart.CROSS_PLAYER + '.' + Fields.Player.ID);
        int naughtPlayerId = response.extract().path(GameStart.NAUGHT_PLAYER + '.' + Fields.Player.ID);

        List<CellDto> cells = new ArrayList<>();
        FieldResponse fieldResponse = new FieldResponse().setCells(cells);

        GameApi.
                getField(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(FieldResponseSpec.of(fieldResponse).withoutRoot());

        MoveRequest moveRequest = GameApi.createMoveRequest((long) crossPlayerId, 2, 2);

        MoveResponse moveResponse = new MoveResponse()
                .setStatus(MoveStatus.SUCCESS)
                .setGameFinished(false);

        GameApi.
                post(gameId.longValue(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(moveResponse).withoutRoot());

        cells.add(new CellDto().setSymbol(GameSymbol.CROSS).setHorizontalPosition(2).setVerticalPosition(2));

        GameApi.
                getField(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(FieldResponseSpec.of(fieldResponse).withoutRoot());

        moveRequest = GameApi.createMoveRequest((long) naughtPlayerId, 1, 1);

        GameApi.
                post(gameId.longValue(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(moveResponse).withoutRoot());

        cells.add(new CellDto().setSymbol(GameSymbol.NAUGHT).setHorizontalPosition(1).setVerticalPosition(1));

        GameApi.
                getField(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(FieldResponseSpec.of(fieldResponse).withoutRoot());

        moveRequest = GameApi.createMoveRequest((long) crossPlayerId, 1, 3);

        GameApi.
                post(gameId.longValue(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(moveResponse).withoutRoot());

        cells.add(new CellDto().setSymbol(GameSymbol.CROSS).setHorizontalPosition(1).setVerticalPosition(3));

        GameApi.
                getField(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(FieldResponseSpec.of(fieldResponse).withoutRoot());

        moveRequest = GameApi.createMoveRequest((long) naughtPlayerId, 3, 1);

        GameApi.
                post(gameId.longValue(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(moveResponse).withoutRoot());

        cells.add(new CellDto().setSymbol(GameSymbol.NAUGHT).setHorizontalPosition(3).setVerticalPosition(1));

        GameApi.
                getField(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(FieldResponseSpec.of(fieldResponse).withoutRoot());

        moveRequest = GameApi.createMoveRequest((long) crossPlayerId, 2, 1);

        GameApi.
                post(gameId.longValue(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(moveResponse).withoutRoot());

        cells.add(new CellDto().setSymbol(GameSymbol.CROSS).setHorizontalPosition(2).setVerticalPosition(1));

        GameApi.
                getField(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(FieldResponseSpec.of(fieldResponse).withoutRoot());

        moveRequest = GameApi.createMoveRequest((long) naughtPlayerId, 2, 3);

        GameApi.
                post(gameId.longValue(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(moveResponse).withoutRoot());

        cells.add(new CellDto().setSymbol(GameSymbol.NAUGHT).setHorizontalPosition(2).setVerticalPosition(3));

        GameApi.
                getField(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(FieldResponseSpec.of(fieldResponse).withoutRoot());

        moveRequest = GameApi.createMoveRequest((long) crossPlayerId, 3, 2);

        moveResponse.setGameFinished(false);

        GameApi.
                post(gameId.longValue(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(moveResponse).withoutRoot());

        cells.add(new CellDto().setSymbol(GameSymbol.CROSS).setHorizontalPosition(3).setVerticalPosition(2));

        GameApi.
                getField(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(FieldResponseSpec.of(fieldResponse).withoutRoot());

        moveRequest = GameApi.createMoveRequest((long) naughtPlayerId, 1, 2);

        moveResponse.setGameFinished(false);

        GameApi.
                post(gameId.longValue(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(moveResponse).withoutRoot());

        cells.add(new CellDto().setSymbol(GameSymbol.NAUGHT).setHorizontalPosition(1).setVerticalPosition(2));

        GameApi.
                getField(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(FieldResponseSpec.of(fieldResponse).withoutRoot());

        moveRequest = GameApi.createMoveRequest((long) crossPlayerId, 3, 3);

        moveResponse.setGameFinished(true);

        GameApi.
                post(gameId.longValue(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(moveResponse).withoutRoot());

        cells.add(new CellDto().setSymbol(GameSymbol.CROSS).setHorizontalPosition(3).setVerticalPosition(3));

        GameApi.
                getField(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(FieldResponseSpec.of(fieldResponse).withoutRoot());

        GameStatusResponse gameStatusResponse = new GameStatusResponse().setResult(GameResult.DRAW);

        GameApi.
                getStatus(gameId.longValue()).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(GameStatusResponseSpec.of(gameStatusResponse).withoutRoot());
    }
}
