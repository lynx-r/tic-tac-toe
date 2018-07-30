package tic_tac_toe.internal;

import java.util.Optional;
import javax.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tic_tac_toe.domain.game.start.GameStartRequest;
import tic_tac_toe.domain.game.start.GameStartResponse;
import tic_tac_toe.entity.Game;
import tic_tac_toe.entity.Player;
import tic_tac_toe.enums.GameStatus;
import tic_tac_toe.mapper.GameStartMapper;
import tic_tac_toe.repository.GameRepository;
import tic_tac_toe.repository.PlayerRepository;

@Service
@RequiredArgsConstructor
public class GameStartServiceInternal {
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final GameStartMapper mapper;

    public GameStartResponse startGame(GameStartRequest request) {
        return isGameCreated(request) ? updateNewGame(request) : createNewGame(request);
    }

    private GameStartResponse createNewGame(GameStartRequest request) {
        Player crossPlayer = findOrCreatePlayer(request.getLogin());
        Game game = new Game()
                .setCrossPlayer(crossPlayer)
                .setGameStatus(GameStatus.NOT_START);
        return mapper.toGameStartResponse(gameRepository.save(game));
    }

    private GameStartResponse updateNewGame(GameStartRequest request) {
        Game game = findCreatedGame(request);
        Player naughtPlayer = findOrCreatePlayer(request.getLogin(), game.getCrossPlayer());
        game.setNaughtPlayer(naughtPlayer)
                .setGameStatus(GameStatus.JUST_START);
        return mapper.toGameStartResponse(gameRepository.save(game));
    }

    private Player findOrCreatePlayer(@NotEmpty String login, Player crossPlayer) {
        if (crossPlayer.getLogin().equals(login)) {
            //TODO: special type
            throw new RuntimeException("Нельзя играть с самим собой");
        }
        return findOrCreatePlayer(login);
    }

    private Player findOrCreatePlayer(String login) {
        Optional<Player> playerIntoBox = playerRepository.findByLogin(login);
        return playerIntoBox.orElseGet(() -> playerRepository.save(new Player().setLogin(login)));
    }

    private Game findCreatedGame(GameStartRequest request) {
        Long gameId = request.getGameId();
        //TODO: special type
        return gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("No such Game"));
    }

    private boolean isGameCreated(GameStartRequest request) {
        return request.getGameId() != null;
    }
}
