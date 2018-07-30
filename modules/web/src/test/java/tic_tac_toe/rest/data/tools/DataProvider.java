package tic_tac_toe.rest.data.tools;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tic_tac_toe.entity.Game;
import tic_tac_toe.entity.Player;
import tic_tac_toe.rest.data.GameBuilder;
import tic_tac_toe.rest.data.PlayerBuilder;

@Component
@RequiredArgsConstructor
public class DataProvider {
    private final RepositoriesContainer repositories;

    public PlayerBuilder player(Player player) {
        return new PlayerBuilder(this, repositories).of(player);
    }

    void build() {}

    public void clear() {
        repositories.clearAll();
    }

    public GameBuilder game(Game game) {
        return new GameBuilder(this, repositories).of(game);
    }
}
