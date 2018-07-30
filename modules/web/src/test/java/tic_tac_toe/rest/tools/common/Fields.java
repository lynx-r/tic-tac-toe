package tic_tac_toe.rest.tools.common;

public interface Fields {

    interface GameStart {
        String GAME_ID = "gameId";
        String CROSS_PLAYER = "crossPlayer";
        String NAUGHT_PLAYER = "naughtPlayer";
        String IS_STARTED = "isStarted";
    }

    interface Player {
        String ID = "playerId";
        String LOGIN = "login";
    }
}
