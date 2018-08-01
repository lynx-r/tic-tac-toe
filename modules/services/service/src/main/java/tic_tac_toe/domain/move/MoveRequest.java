package tic_tac_toe.domain.move;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class MoveRequest {
    @JsonProperty("playerId")
    private Long playerId;

    @JsonProperty("horizontalPosition")
    private Integer horizontalPosition;

    @JsonProperty("verticalPosition")
    private Integer verticalPosition;
}
