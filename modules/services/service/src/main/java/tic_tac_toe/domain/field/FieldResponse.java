package tic_tac_toe.domain.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class FieldResponse {
    @JsonProperty("field")
    private List<CellDto> cells;
}
