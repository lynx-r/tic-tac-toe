package tic_tac_toe.rest.data.prototype;

import java.util.concurrent.atomic.AtomicLong;
import tic_tac_toe.entity.Player;

public interface PlayerPrototype {
    AtomicLong COUNTER = new AtomicLong(10_000);

    static Player defaultPlayer() {
        return new Player()
                .setLogin("Login: " + COUNTER.incrementAndGet());
    }
}
