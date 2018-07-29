package tic_tac_toe;

import tic_tac_toe.domain.FieldResponse;
import tic_tac_toe.domain.GameStatusResponse;
import tic_tac_toe.domain.MoveRequest;
import tic_tac_toe.domain.MoveResponse;

public interface GameService {
    MoveResponse makeMove(Long id, MoveRequest request);

    FieldResponse getFieldState(Long id);

    GameStatusResponse getGameStatus(Long id);
}
