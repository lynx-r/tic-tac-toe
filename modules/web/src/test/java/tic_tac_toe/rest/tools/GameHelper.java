package tic_tac_toe.rest.tools;

import tic_tac_toe.entity.Game;

import static tic_tac_toe.rest.data.prototype.GamePrototype.defaultGame;

public class GameHelper {
    public static Game createOne() {
        return defaultGame();
    }
}
