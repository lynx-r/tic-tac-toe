package tic_tac_toe.rest.data.spec;

import io.restassured.builder.ResponseSpecBuilder;
import javax.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import tic_tac_toe.domain.field.FieldResponse;
import tic_tac_toe.rest.tools.common.Fields.Field;

@RequiredArgsConstructor(staticName = "of")
public class FieldResponseSpec extends ResponseSpec {
    private final FieldResponse response;

    @Override
    protected void applySpec(ResponseSpecBuilder specBuilder, @Nullable String root) {
        specBuilder.addResponseSpecification(CellDtoSpec.of(response.getCells()).withRoot(root, Field.FIELD));
    }
}
