package tic_tac_toe.domain.move;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class MoveResponse {
    @JsonProperty("status")
    private MoveStatus status;

    @JsonProperty("isGameFinished")
    private boolean gameFinished;

    public enum MoveStatus {
        @JsonProperty("success")
        SUCCESS,
        @JsonProperty("failed")
        FAILED
    }
}
