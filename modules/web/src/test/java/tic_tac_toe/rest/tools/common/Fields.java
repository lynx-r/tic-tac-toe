package tic_tac_toe.rest.tools.common;

public interface Fields {

    interface GameStart {
        String GAME_ID = "gameId";
        String CROSS_PLAYER = "crossPlayer";
        String NAUGHT_PLAYER = "naughtPlayer";
        String IS_STARTED = "isStarted";
    }

    interface MoveMake {
        String STATUS = "status";
        String IS_GAME_FINISHED = "isGameFinished";
    }

    interface Field {
        String FIELD = "field";

        interface Cell {
            String HORIZONTAL_POSITION = "horizontalPosition";
            String VERTICAL_POSITION = "verticalPosition";
            String SYMBOL = "symbol";
        }
    }

    interface Player {
        String ID = "playerId";
        String LOGIN = "login";
    }

    interface Error {
        String ERROR_NAME = "errorName";
        String USER_MESSAGE = "userMessage";
    }
}
