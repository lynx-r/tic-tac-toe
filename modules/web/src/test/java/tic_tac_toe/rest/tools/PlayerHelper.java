package tic_tac_toe.rest.tools;

import tic_tac_toe.entity.Player;

import static tic_tac_toe.rest.data.prototype.PlayerPrototype.defaultPlayer;

public class PlayerHelper {
    public static Player createOne() {
        return defaultPlayer();
    }
}
