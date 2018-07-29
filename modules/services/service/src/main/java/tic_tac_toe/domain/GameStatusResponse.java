package tic_tac_toe.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import tic_tac_toe.enums.GameResult;

@Getter
@Setter
@Accessors(chain = true)
public class GameStatusResponse {
    @JsonProperty("result")
    private GameResult result;
}
