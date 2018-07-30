package tic_tac_toe.domain.player;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PlayerDto {
    @JsonProperty("playerId")
    private Long id;

    @JsonProperty("login")
    private String login;
}
