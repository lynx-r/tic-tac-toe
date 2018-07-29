package tic_tac_toe.config;

public interface Urls {

    interface Game {
        String ID = "{id}";

        interface GameStart {
            String PART = "start";
        }

        interface GameFlow {
            String PART = "game";
            String FULL = PART + "/" + ID;

            interface Field {
                String PART = "field";
                String FULL = GameFlow.FULL + "/" + PART;
            }

            interface Status {
                String PART = "status";
                String FULL = GameFlow.FULL + "/" + PART;
            }
        }
    }
}
