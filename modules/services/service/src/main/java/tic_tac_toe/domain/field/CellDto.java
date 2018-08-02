package tic_tac_toe.domain.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import tic_tac_toe.enums.GameSymbol;

@Getter
@Setter
@Accessors(chain = true)
public class CellDto {
    @JsonProperty("horizontalPosition")
    private int horizontalPosition;
    @JsonProperty("verticalPosition")
    private int verticalPosition;
    @JsonProperty("symbol")
    private GameSymbol symbol;
}
