package tic_tac_toe.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class GameStartRequest {

    @NotEmpty
    @JsonProperty("login")
    private String login;

    @JsonProperty("gameId")
    private Long gameId;
}
