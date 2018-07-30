package tic_tac_toe;

import org.springframework.stereotype.Service;
import tic_tac_toe.domain.field.FieldResponse;
import tic_tac_toe.domain.game.GameStatusResponse;
import tic_tac_toe.domain.move.MoveRequest;
import tic_tac_toe.domain.move.MoveResponse;

@Service
public class GameServiceImpl implements GameService {
    @Override
    public MoveResponse makeMove(Long id, MoveRequest request) {
        return null;
    }

    @Override
    public FieldResponse getFieldState(Long id) {
        return null;
    }

    @Override
    public GameStatusResponse getGameStatus(Long id) {
        return null;
    }
}
