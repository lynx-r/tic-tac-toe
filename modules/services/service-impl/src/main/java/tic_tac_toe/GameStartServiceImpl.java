package tic_tac_toe;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tic_tac_toe.domain.game.start.GameStartRequest;
import tic_tac_toe.domain.game.start.GameStartResponse;
import tic_tac_toe.internal.GameStartServiceInternal;

@Service
@RequiredArgsConstructor
public class GameStartServiceImpl implements GameStartService {
    private final GameStartServiceInternal gameStartServiceInternal;

    @Override
    public GameStartResponse startGame(GameStartRequest request) {
       return gameStartServiceInternal.startGame(request);
    }
}
