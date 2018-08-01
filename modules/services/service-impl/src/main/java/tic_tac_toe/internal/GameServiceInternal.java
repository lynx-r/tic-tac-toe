package tic_tac_toe.internal;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tic_tac_toe.domain.move.MoveRequest;
import tic_tac_toe.entity.Game;
import tic_tac_toe.entity.Move;
import tic_tac_toe.enums.GameResult;
import tic_tac_toe.enums.GameStatus;
import tic_tac_toe.enums.GameSymbol;
import tic_tac_toe.exceptions.ErrorType;
import tic_tac_toe.exceptions.InvalidGameStatusException;
import tic_tac_toe.exceptions.MoveOrderException;
import tic_tac_toe.exceptions.NoBlankCellException;
import tic_tac_toe.exceptions.NoSuchGameException;
import tic_tac_toe.exceptions.PositionBusyException;
import tic_tac_toe.internal.cache.GameFinishReasonCache;
import tic_tac_toe.repository.GameRepository;
import tic_tac_toe.spec.GameSpec;

@Service
@RequiredArgsConstructor
public class GameServiceInternal {
    private final static int MAX_NUMBER_OF_MOVES = 9;
    private static final int MIN_NUMBER_FOR_FINISH = 5;
    private static final int FIELD_SIZE = 3;

    private final GameRepository gameRepository;
    private final GameFinishReasonCache cache;

    public Game findGame(Long id) {
        Specification<Game> spec = GameSpec.idIs(id)
                .and(GameSpec.fetchCrossPlayer())
                .and(GameSpec.fetchNaughtPlayer())
                .and(GameSpec.fetchMoves());

        return gameRepository.findOne(spec).orElseThrow(() -> new NoSuchGameException(id));
    }

    public void validateGameStatus(Game game) {
        if (game.getGameStatus() == GameStatus.NOT_START) {
            throw new InvalidGameStatusException(
                    ErrorType.INVALID_GAME_STATUS, String.format("Game with id=%d is not started", game.getId()));
        }
        if (game.getGameStatus() == GameStatus.FINISHED) {
            throw new InvalidGameStatusException(
                    ErrorType.INVALID_GAME_STATUS, String.format("Game with id=%d is already finished", game.getId()));
        }
    }

    public void validateGameMoves(Game game, MoveRequest request) {
        if (game.getMoves().size() >= MAX_NUMBER_OF_MOVES) {
            throw new NoBlankCellException(ErrorType.NO_BLANK_CELL);
        }
        Long activePlayerId = request.getPlayerId();
        Long crossPlayerId = game.getCrossPlayer().getId();
        Long naughtPlayerId = game.getNaughtPlayer().getId();

        Long idOfPlayerShouldMakeMove = game.getMoves().size() % 2 == 0 ? crossPlayerId : naughtPlayerId;

        if (!activePlayerId.equals(idOfPlayerShouldMakeMove)) {
            throw new MoveOrderException();
        }

        boolean isInvalidMove = game.getMoves()
                .parallelStream()
                .anyMatch(move -> move.getVerticalPosition() == request.getVerticalPosition()
                        && move.getHorizontalPosition() == request.getHorizontalPosition());

        if (isInvalidMove) {
            throw new PositionBusyException(request.getHorizontalPosition(), request.getVerticalPosition());
        }
    }

    public void createNewMove(Game game, MoveRequest request) {
        Move move = new Move()
                .setMoveNumber(game.getMoves().size() + 1)
                .setGame(game)
                .setGameSymbol(game.getMoves().size() % 2 == 0 ? GameSymbol.CROSS : GameSymbol.NAUGHT)
                .setHorizontalPosition(request.getHorizontalPosition())
                .setVerticalPosition(request.getVerticalPosition());

        game.getMoves().add(move);
    }

    public boolean defineIsGameFinished(Game game) {
        cache.initCache();

        List<Move> moves = game.getMoves();
        if (moves.size() < MIN_NUMBER_FOR_FINISH) {
            return false;
        }

        if (isFilledVertical(moves) ||
                isFilledHorizontal(moves) ||
                isFilledMainDiagonal(moves) ||
                isFilledCollateralDiagonal(moves)) {
            return true;
        }

        if (moves.size() == MAX_NUMBER_OF_MOVES) {
            cache.setDraw(true);
            return true;
        }

        return false;
    }

    public GameResult defineGameResult(Game game) {
        return cache.isDraw() ? GameResult.DRAW :
                game.getMoves().size() % 2 == 1 ? GameResult.CROSS_WON : GameResult.NAUGHT_WON;
    }

    public void updateGame(Game game) {
        gameRepository.saveAndFlush(game);
    }

    private boolean isFilledCollateralDiagonal(List<Move> moves) {
        GameSymbol gameSymbol = moves.size() % 2 == 1 ? GameSymbol.CROSS : GameSymbol.NAUGHT;
        for (int i = 1; i <= FIELD_SIZE; i++) {
            int horizontalIndex = i;
            int verticalIndex = FIELD_SIZE - i + 1;

            if (moves
                    .parallelStream()
                    .noneMatch(move -> move.getHorizontalPosition() == horizontalIndex
                            && move.getVerticalPosition() == verticalIndex
                            && move.getGameSymbol() == gameSymbol))
            {
                return false;
            }
        }

        return true;
    }

    private boolean isFilledMainDiagonal(List<Move> moves) {
        GameSymbol gameSymbol = moves.size() % 2 == 1 ? GameSymbol.CROSS : GameSymbol.NAUGHT;
        for (int i = 1; i <= FIELD_SIZE; i++) {
            int horizontalIndex = i;
            int verticalIndex = i;

            if (moves
                    .parallelStream()
                    .noneMatch(
                            move -> move.getHorizontalPosition() == horizontalIndex
                                    && move.getVerticalPosition() == verticalIndex
                                    && move.getGameSymbol() == gameSymbol))
            {
                return false;
            }
        }
        return true;
    }

    private boolean isFilledHorizontal(List<Move> moves) {
        GameSymbol gameSymbol = moves.size() % 2 == 1 ? GameSymbol.CROSS : GameSymbol.NAUGHT;
        for (int i = 1; i <= FIELD_SIZE; i++) {
            int horizontalIndex = i;

            long count = moves
                    .parallelStream()
                    .filter(
                            move -> move.getHorizontalPosition() == horizontalIndex
                                    && move.getGameSymbol() == gameSymbol
                    ).count();
            if (count == FIELD_SIZE) {
                return true;
            }
        }
        return false;
    }

    private boolean isFilledVertical(List<Move> moves) {
        GameSymbol gameSymbol = moves.size() % 2 == 1 ? GameSymbol.CROSS : GameSymbol.NAUGHT;
        for (int i = 1; i <= FIELD_SIZE; i++) {
            int verticalIndex = i;

            long count = moves
                    .parallelStream()
                    .filter(
                            move -> move.getVerticalPosition() == verticalIndex
                                    && move.getGameSymbol() == gameSymbol
                    ).count();
            if (count == FIELD_SIZE) {
                return true;
            }
        }
        return false;
    }
}
