package tic_tac_toe.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum GameResult {
    @JsonProperty("crossWin")
    CROSS_WON,
    @JsonProperty("naughtWin")
    NAUGHT_WON,
    @JsonProperty("draw")
    DRAW
}
