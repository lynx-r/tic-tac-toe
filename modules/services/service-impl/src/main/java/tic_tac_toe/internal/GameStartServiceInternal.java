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
import tic_tac_toe.exceptions.NoSuchGameException;
import tic_tac_toe.exceptions.SameSecondPlayerException;
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

    public GameStartResponse startGame(Long gameId) {
        return mapper.toGameStartResponse(findCreatedGame(gameId));
    }

    private GameStartResponse createNewGame(GameStartRequest request) {
        Player crossPlayer = findOrCreatePlayer(request.getLogin());
        Game game = new Game()
                .setCrossPlayer(crossPlayer)
                .setGameStatus(GameStatus.NOT_START);
        return mapper.toGameStartResponse(gameRepository.save(game));
    }

    private GameStartResponse updateNewGame(GameStartRequest request) {
        Game game = findCreatedGame(request.getGameId());
        Player naughtPlayer = findOrCreatePlayer(request.getLogin(), game.getCrossPlayer());
        game.setNaughtPlayer(naughtPlayer)
                .setGameStatus(GameStatus.JUST_START);
        return mapper.toGameStartResponse(gameRepository.save(game));
    }

    private Player findOrCreatePlayer(@NotEmpty String login, Player crossPlayer) {
        if (crossPlayer.getLogin().equals(login)) {
            throw new SameSecondPlayerException(login);
        }
        return findOrCreatePlayer(login);
    }

    private Player findOrCreatePlayer(String login) {
        Optional<Player> playerIntoBox = playerRepository.findByLogin(login);
        return playerIntoBox.orElseGet(() -> playerRepository.save(new Player().setLogin(login)));
    }

    private Game findCreatedGame(Long gameId) {
        return gameRepository.findById(gameId).orElseThrow(() -> new NoSuchGameException(gameId));
    }

    private boolean isGameCreated(GameStartRequest request) {
        return request.getGameId() != null;
    }
}
