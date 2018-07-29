package tic_tac_toe;

import tic_tac_toe.domain.GameStartRequest;
import tic_tac_toe.domain.GameStartResponse;

public interface GameStartService {
    GameStartResponse startGame(GameStartRequest request);
}
