package tic_tac_toe;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tic_tac_toe.domain.field.CellDto;
import tic_tac_toe.domain.field.FieldResponse;
import tic_tac_toe.domain.game.GameStatusResponse;
import tic_tac_toe.domain.move.MoveRequest;
import tic_tac_toe.domain.move.MoveResponse;
import tic_tac_toe.domain.move.MoveResponse.MoveStatus;
import tic_tac_toe.entity.Game;
import tic_tac_toe.enums.GameResult;
import tic_tac_toe.enums.GameStatus;
import tic_tac_toe.internal.GameServiceInternal;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameServiceInternal gameServiceInternal;

    @Override
    public MoveResponse makeMove(Long id, MoveRequest request) {
        Game game = gameServiceInternal.findGame(id);

        gameServiceInternal.validateGameStatus(game);
        gameServiceInternal.validateGameMoves(game, request);

        gameServiceInternal.createNewMove(game, request);

        boolean isGameFinished = gameServiceInternal.defineIsGameFinished(game);

        if (isGameFinished) {
            game.setGameStatus(GameStatus.FINISHED);
            GameResult gameResult = gameServiceInternal.defineGameResult(game);
            game.setGameResult(gameResult);
        }

        gameServiceInternal.updateGame(game);

        return new MoveResponse()
                .setGameFinished(isGameFinished)
                .setStatus(MoveStatus.SUCCESS);
    }

    @Override
    public FieldResponse getFieldState(Long id) {
        Game game = gameServiceInternal.findGame(id);

        List<CellDto> busyCells = game.getMoves()
                                    .stream()
                                    .map(move -> new CellDto()
                                            .setHorizontalPosition(move.getHorizontalPosition())
                                            .setVerticalPosition(move.getVerticalPosition())
                                            .setSymbol(move.getGameSymbol()))
                                    .collect(Collectors.toList());

        return new FieldResponse()
                .setCells(busyCells);
    }

    @Override
    public GameStatusResponse getGameStatus(Long id) {
        Game game = gameServiceInternal.findGame(id);
        return new GameStatusResponse().setResult(game.getGameResult());
    }
}
