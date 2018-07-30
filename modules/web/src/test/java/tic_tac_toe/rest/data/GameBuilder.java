package tic_tac_toe.rest.data;

import tic_tac_toe.entity.Game;
import tic_tac_toe.repository.GameRepository;
import tic_tac_toe.rest.data.tools.Builder;
import tic_tac_toe.rest.data.tools.DataProvider;
import tic_tac_toe.rest.data.tools.RepositoriesContainer;

public class GameBuilder extends Builder {
    private final GameRepository gameRepository;

    public GameBuilder(DataProvider dataProvider, RepositoriesContainer container) {
        super(dataProvider);
        this.gameRepository = container.getGameRepository();
    }

    public GameBuilder of(Game game) {
        if (game != null) {
            Game gameFromDb = gameRepository.saveAndFlush(game);
            game.setId(gameFromDb.getId());
        }
        return this;
    }
}
