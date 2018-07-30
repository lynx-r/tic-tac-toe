package tic_tac_toe.domain.move;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoveRequest {
    @JsonProperty("gameId")
    private Long gameId;

    @JsonProperty("playerId")
    private Long playerId;

    @JsonProperty("horizontalPosition")
    private Integer horizontalPosition;

    @JsonProperty("verticalPosition")
    private Integer verticalPosition;
}
