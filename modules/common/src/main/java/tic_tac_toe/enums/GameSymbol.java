package tic_tac_toe.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum GameSymbol {
    @JsonProperty("cross")
    CROSS,
    @JsonProperty("naught")
    NAUGHT
}
