package tic_tac_toe.rest;

import java.util.List;
import org.apache.http.HttpStatus;
import org.junit.Test;
import tic_tac_toe.domain.move.MoveRequest;
import tic_tac_toe.domain.move.MoveResponse;
import tic_tac_toe.domain.move.MoveResponse.MoveStatus;
import tic_tac_toe.entity.Game;
import tic_tac_toe.entity.Move;
import tic_tac_toe.entity.Player;
import tic_tac_toe.enums.GameStatus;
import tic_tac_toe.exceptions.ErrorType;
import tic_tac_toe.exceptions.PositionBusyException;
import tic_tac_toe.rest.api.GameApi;
import tic_tac_toe.rest.data.spec.ErrorSpec;
import tic_tac_toe.rest.data.spec.MoveResponseSpec;
import tic_tac_toe.rest.tools.GameHelper;
import tic_tac_toe.rest.tools.MoveHelper;
import tic_tac_toe.rest.tools.PlayerHelper;
import tic_tac_toe.rest.tools.common.IntegrationTest;

public class GameControllerIT extends IntegrationTest {

    @Test
    public void checkFirstMove() {
        Player crossPlayer = PlayerHelper.createOne();
        Player naughtPlayer = PlayerHelper.createOne();

        Game game = GameHelper.createOne()
                .setCrossPlayer(crossPlayer)
                .setNaughtPlayer(naughtPlayer)
                .setGameStatus(GameStatus.JUST_START);

        data.game(game).build();

        MoveRequest moveRequest =
                GameApi.createMoveRequest(game.getCrossPlayer().getId(), 2, 2);

        MoveResponse response = new MoveResponse()
                .setStatus(MoveStatus.SUCCESS)
                .setGameFinished(false);

        GameApi.
                post(game.getId(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(response).withoutRoot());
    }

    @Test
    public void checkFourthMove() {
        Player crossPlayer = PlayerHelper.createOne();
        Player naughtPlayer = PlayerHelper.createOne();

        List<Move> moves = MoveHelper.createMoves(3);

        Game game = GameHelper.createOne()
                .setCrossPlayer(crossPlayer)
                .setNaughtPlayer(naughtPlayer)
                .setMoves(moves)
                .setGameStatus(GameStatus.IN_PROGRESS);

        for (Move move : moves) {
            move.setGame(game);
        }

        data.game(game).build();

        MoveRequest moveRequest =
                GameApi.createMoveRequest(game.getNaughtPlayer().getId(), 2, 1);

        MoveResponse expected = new MoveResponse()
                .setStatus(MoveStatus.SUCCESS)
                .setGameFinished(false);

        GameApi.
                post(game.getId(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(expected).withoutRoot());
    }

    @Test
    public void checkLastMoveForDraw() {
        Player crossPlayer = PlayerHelper.createOne();
        Player naughtPlayer = PlayerHelper.createOne();

        List<Move> moves = MoveHelper.createEightMovesForDraw();

        Game game = GameHelper.createOne()
                .setCrossPlayer(crossPlayer)
                .setNaughtPlayer(naughtPlayer)
                .setMoves(moves)
                .setGameStatus(GameStatus.IN_PROGRESS);

        for (Move move : moves) {
            move.setGame(game);
        }

        data.game(game).build();

        MoveRequest moveRequest =
                GameApi.createMoveRequest(game.getCrossPlayer().getId(), 1, 3);

        MoveResponse expected = new MoveResponse()
                .setStatus(MoveStatus.SUCCESS)
                .setGameFinished(true);

        GameApi.
                post(game.getId(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(expected).withoutRoot());
    }

    @Test
    public void checkFiveMoveForWinHorizontal() {
        Player crossPlayer = PlayerHelper.createOne();
        Player naughtPlayer = PlayerHelper.createOne();

        List<Move> moves = MoveHelper.createFourMovesForWin();

        Game game = GameHelper.createOne()
                .setCrossPlayer(crossPlayer)
                .setNaughtPlayer(naughtPlayer)
                .setMoves(moves)
                .setGameStatus(GameStatus.IN_PROGRESS);

        for (Move move : moves) {
            move.setGame(game);
        }

        data.game(game).build();

        MoveRequest moveRequest =
                GameApi.createMoveRequest(game.getCrossPlayer().getId(), 1, 3);

        MoveResponse expected = new MoveResponse()
                .setStatus(MoveStatus.SUCCESS)
                .setGameFinished(true);

        GameApi.
                post(game.getId(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(expected).withoutRoot());
    }

    @Test
    public void checkSixMoveForWinVertical() {
        Player crossPlayer = PlayerHelper.createOne();
        Player naughtPlayer = PlayerHelper.createOne();

        List<Move> moves = MoveHelper.createFiveMovesForWin();

        Game game = GameHelper.createOne()
                .setCrossPlayer(crossPlayer)
                .setNaughtPlayer(naughtPlayer)
                .setMoves(moves)
                .setGameStatus(GameStatus.IN_PROGRESS);

        for (Move move : moves) {
            move.setGame(game);
        }

        data.game(game).build();

        MoveRequest moveRequest =
                GameApi.createMoveRequest(game.getNaughtPlayer().getId(), 3, 2);

        MoveResponse expected = new MoveResponse()
                .setStatus(MoveStatus.SUCCESS)
                .setGameFinished(true);

        GameApi.
                post(game.getId(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(expected).withoutRoot());
    }

    @Test
    public void checkSevenMoveForWinMainDiagonal() {
        Player crossPlayer = PlayerHelper.createOne();
        Player naughtPlayer = PlayerHelper.createOne();

        List<Move> moves = MoveHelper.createSixMovesForWin();

        Game game = GameHelper.createOne()
                .setCrossPlayer(crossPlayer)
                .setNaughtPlayer(naughtPlayer)
                .setMoves(moves)
                .setGameStatus(GameStatus.IN_PROGRESS);

        for (Move move : moves) {
            move.setGame(game);
        }

        data.game(game).build();

        MoveRequest moveRequest =
                GameApi.createMoveRequest(game.getCrossPlayer().getId(), 3, 3);

        MoveResponse expected = new MoveResponse()
                .setStatus(MoveStatus.SUCCESS)
                .setGameFinished(true);

        GameApi.
                post(game.getId(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(expected).withoutRoot());
    }

    @Test
    public void checkLastMoveForWinColDiagonal() {
        Player crossPlayer = PlayerHelper.createOne();
        Player naughtPlayer = PlayerHelper.createOne();

        List<Move> moves = MoveHelper.createEightMovesForWin();

        Game game = GameHelper.createOne()
                .setCrossPlayer(crossPlayer)
                .setNaughtPlayer(naughtPlayer)
                .setMoves(moves)
                .setGameStatus(GameStatus.IN_PROGRESS);

        for (Move move : moves) {
            move.setGame(game);
        }

        data.game(game).build();

        MoveRequest moveRequest =
                GameApi.createMoveRequest(game.getCrossPlayer().getId(), 3, 1);

        MoveResponse expected = new MoveResponse()
                .setStatus(MoveStatus.SUCCESS)
                .setGameFinished(true);

        GameApi.
                post(game.getId(), moveRequest).
                then().
                statusCode(HttpStatus.SC_OK).
                spec(MoveResponseSpec.of(expected).withoutRoot());
    }

    @Test
    public void checkGameNotFoundShouldFail() {
        Player crossPlayer = PlayerHelper.createOne();
        Player naughtPlayer = PlayerHelper.createOne();

        Game game = GameHelper.createOne()
                .setCrossPlayer(crossPlayer)
                .setNaughtPlayer(naughtPlayer)
                .setGameStatus(GameStatus.JUST_START);

        data.game(game).build();

        MoveRequest moveRequest =
                GameApi.createMoveRequest(game.getCrossPlayer().getId(), 3, 1);

        GameApi.
                post(-33L, moveRequest).
                then().
                statusCode(HttpStatus.SC_NOT_FOUND).
                spec(ErrorSpec.applyErrorSpec(ErrorType.NO_SUCH_GAME, String.format("No such game with ID=%d", -33L)));
    }

    @Test
    public void checkGameNotStartedShouldFail() {
        Player crossPlayer = PlayerHelper.createOne();

        Game game = GameHelper.createOne()
                .setCrossPlayer(crossPlayer)
                .setGameStatus(GameStatus.NOT_START);

        data.game(game).build();

        MoveRequest moveRequest =
                GameApi.createMoveRequest(game.getCrossPlayer().getId(), 3, 1);

        GameApi.
                post(game.getId(), moveRequest).
                then().
                statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).
                spec(ErrorSpec.applyErrorSpec(
                        ErrorType.INVALID_GAME_STATUS, String.format("Game with id=%d is not started", game.getId())));
    }

    @Test
    public void checkGameAlreadyFinishedShouldFail() {
        Player crossPlayer = PlayerHelper.createOne();
        Player naughtPlayer = PlayerHelper.createOne();

        Game game = GameHelper.createOne()
                .setCrossPlayer(crossPlayer)
                .setNaughtPlayer(naughtPlayer)
                .setGameStatus(GameStatus.FINISHED);

        data.game(game).build();

        MoveRequest moveRequest =
                GameApi.createMoveRequest(game.getCrossPlayer().getId(), 3, 1);

        GameApi.
                post(game.getId(), moveRequest).
                then().
                statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).
                spec(ErrorSpec.applyErrorSpec(
                        ErrorType.INVALID_GAME_STATUS,
                        String.format("Game with id=%d is already finished", game.getId())));
    }

    @Test
    public void checkGameWithoutEmptyCellsShouldFail() {
        Player crossPlayer = PlayerHelper.createOne();
        Player naughtPlayer = PlayerHelper.createOne();

        List<Move> moves = MoveHelper.createMoves(9);

        Game game = GameHelper.createOne()
                .setCrossPlayer(crossPlayer)
                .setNaughtPlayer(naughtPlayer)
                .setMoves(moves)
                .setGameStatus(GameStatus.IN_PROGRESS);

        for (Move move : moves) {
            move.setGame(game);
        }

        data.game(game).build();

        MoveRequest moveRequest =
                GameApi.createMoveRequest(game.getNaughtPlayer().getId(), 3, 1);

        GameApi.
                post(game.getId(), moveRequest).
                then().
                statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).
                spec(ErrorSpec.applyErrorSpec(
                        ErrorType.NO_BLANK_CELL, "No Blank cells on the field"));
    }

    @Test
    public void checkNotYourMoveShouldFail() {
        Player crossPlayer = PlayerHelper.createOne();
        Player naughtPlayer = PlayerHelper.createOne();

        Game game = GameHelper.createOne()
                .setCrossPlayer(crossPlayer)
                .setNaughtPlayer(naughtPlayer)
                .setGameStatus(GameStatus.JUST_START);

        data.game(game).build();

        MoveRequest moveRequest =
                GameApi.createMoveRequest(game.getNaughtPlayer().getId(), 3, 1);

        GameApi.
                post(game.getId(), moveRequest).
                then().
                statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).
                spec(ErrorSpec.applyErrorSpec(ErrorType.MOVE_ORDER, "Not your move"));
    }

    @Test
    public void checkPositionIsBusyShouldFail() {
        Player crossPlayer = PlayerHelper.createOne();
        Player naughtPlayer = PlayerHelper.createOne();

        List<Move> moves = MoveHelper.createMoves(1);

        Game game = GameHelper.createOne()
                .setCrossPlayer(crossPlayer)
                .setNaughtPlayer(naughtPlayer)
                .setMoves(moves)
                .setGameStatus(GameStatus.IN_PROGRESS);

        for (Move move : moves) {
            move.setGame(game);
        }

        data.game(game).build();

        MoveRequest moveRequest =
                GameApi.createMoveRequest(game.getNaughtPlayer().getId(), 1, 1);

        GameApi.
                post(game.getId(), moveRequest).
                then().
                statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).
                spec(ErrorSpec.applyErrorSpec(ErrorType.POSITION_BUSY,
                        String.format(PositionBusyException.MESSAGE,
                                moveRequest.getHorizontalPosition(),
                                moveRequest.getVerticalPosition())));
    }
}
