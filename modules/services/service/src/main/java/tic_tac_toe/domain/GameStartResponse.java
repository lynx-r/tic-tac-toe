package tic_tac_toe.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class GameStartResponse {
    @JsonProperty("gameId")
    private Long gameId;

    @JsonProperty("crossPlayer")
    private PlayerDto crossPlayer;

    @JsonProperty("naughtPlayer")
    private PlayerDto naughtPlayer;

    @JsonProperty("isStarted")
    private boolean isStarted;
}
