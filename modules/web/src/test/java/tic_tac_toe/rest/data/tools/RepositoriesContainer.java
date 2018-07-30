package tic_tac_toe.rest.data.tools;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tic_tac_toe.repository.GameRepository;
import tic_tac_toe.repository.MoveRepository;
import tic_tac_toe.repository.PlayerRepository;

@Component
@RequiredArgsConstructor
@Getter
public class RepositoriesContainer {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final MoveRepository moveRepository;

    @Transactional
    public void clearAll() {
        moveRepository.deleteAllInBatch();
        gameRepository.deleteAllInBatch();
        playerRepository.deleteAllInBatch();
    }
}
