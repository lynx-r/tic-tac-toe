package tic_tac_toe.rest.data.prototype;

import java.util.concurrent.atomic.AtomicLong;
import tic_tac_toe.entity.Game;
import tic_tac_toe.enums.GameStatus;

public interface GamePrototype {
    AtomicLong COUNTER = new AtomicLong(10_000);

    static Game defaultGame() {
        return new Game()
                .setGameStatus(GameStatus.NOT_START);
    }
}
