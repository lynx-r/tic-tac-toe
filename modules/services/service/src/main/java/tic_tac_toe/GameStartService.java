package tic_tac_toe;

import tic_tac_toe.domain.game.start.GameStartRequest;
import tic_tac_toe.domain.game.start.GameStartResponse;

public interface GameStartService {
    GameStartResponse startGame(GameStartRequest request);

    GameStartResponse startGame(Long gameId);
}
