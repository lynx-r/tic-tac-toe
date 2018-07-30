package tic_tac_toe.mapper;

import org.mapstruct.Mapper;
import tic_tac_toe.domain.game.start.GameStartResponse;
import tic_tac_toe.domain.player.PlayerDto;
import tic_tac_toe.entity.Game;
import tic_tac_toe.entity.Player;
import tic_tac_toe.enums.GameStatus;

@Mapper(componentModel = "spring")
public interface GameStartMapper {

    default GameStartResponse toGameStartResponse(Game game) {
        if (game == null) {
            return null;
        }

        return new GameStartResponse()
                .setCrossPlayer(playerToPlayerDto(game.getCrossPlayer()))
                .setNaughtPlayer(playerToPlayerDto(game.getNaughtPlayer()))
                .setGameId(game.getId())
                .setStarted(game.getGameStatus() != GameStatus.NOT_START);
    }

    default PlayerDto playerToPlayerDto(Player player) {
        if (player == null) {
            return null;
        }

        PlayerDto playerDto = new PlayerDto();

        playerDto.setId(player.getId());
        playerDto.setLogin(player.getLogin());

        return playerDto;
    }
}
