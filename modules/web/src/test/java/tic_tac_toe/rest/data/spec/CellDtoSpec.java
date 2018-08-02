package tic_tac_toe.rest.data.spec;

import io.restassured.builder.ResponseSpecBuilder;
import java.util.Collection;
import javax.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import tic_tac_toe.domain.field.CellDto;
import tic_tac_toe.rest.tools.common.Fields.Field.Cell;

import static org.hamcrest.Matchers.containsInAnyOrder;

@RequiredArgsConstructor(staticName = "of")
public class CellDtoSpec extends ResponseSpec {
    private final Collection<CellDto> cells;

    @Override
    protected void applySpec(ResponseSpecBuilder specBuilder, @Nullable String root) {
        specBuilder.
                expectBody(Cell.HORIZONTAL_POSITION,
                        containsInAnyOrder(cells.stream().
                                map(CellDto::getHorizontalPosition).
                                toArray())).
                expectBody(Cell.VERTICAL_POSITION,
                        containsInAnyOrder(cells.stream().
                                map(CellDto::getVerticalPosition).
                                toArray())).
                expectBody(Cell.SYMBOL,
                        containsInAnyOrder(cells.stream().
                                map(cellDto -> cellDto.getSymbol().name().toLowerCase()).
                                toArray()));
    }
}
