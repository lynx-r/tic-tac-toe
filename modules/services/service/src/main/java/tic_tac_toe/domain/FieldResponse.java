package tic_tac_toe.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import tic_tac_toe.enums.GameSymbol;

@Getter
@Setter
@Accessors(chain = true)
public class FieldResponse {
    @JsonProperty("field")
    private List<Cell> cells;

    @Getter
    @Setter
    @Accessors(chain = true)
    private static class Cell {
        @JsonProperty("horizontalPosition")
        private int horizontalPosition;
        @JsonProperty("verticalPosition")
        private int verticalPosition;
        @JsonProperty("symbol")
        private GameSymbol symbol;
    }
}
