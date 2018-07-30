package tic_tac_toe.rest.data;

import tic_tac_toe.entity.Player;
import tic_tac_toe.repository.PlayerRepository;
import tic_tac_toe.rest.data.tools.Builder;
import tic_tac_toe.rest.data.tools.DataProvider;
import tic_tac_toe.rest.data.tools.RepositoriesContainer;

public class PlayerBuilder extends Builder {
    private final PlayerRepository playerRepository;

    public PlayerBuilder(DataProvider dataProvider, RepositoriesContainer container) {
        super(dataProvider);
        this.playerRepository = container.getPlayerRepository();
    }

    public PlayerBuilder of(Player player) {
        if (player != null) {
            Player playerFromDb = playerRepository.saveAndFlush(player);
            player.setId(playerFromDb.getId());
        }
        return this;
    }
}
