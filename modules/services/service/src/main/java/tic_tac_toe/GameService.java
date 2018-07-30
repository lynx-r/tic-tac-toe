package tic_tac_toe;

import tic_tac_toe.domain.field.FieldResponse;
import tic_tac_toe.domain.game.GameStatusResponse;
import tic_tac_toe.domain.move.MoveRequest;
import tic_tac_toe.domain.move.MoveResponse;

public interface GameService {
    MoveResponse makeMove(Long id, MoveRequest request);

    FieldResponse getFieldState(Long id);

    GameStatusResponse getGameStatus(Long id);
}
