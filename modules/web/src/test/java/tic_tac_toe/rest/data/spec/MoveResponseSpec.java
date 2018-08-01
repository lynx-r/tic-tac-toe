package tic_tac_toe.rest.data.spec;

import io.restassured.builder.ResponseSpecBuilder;
import javax.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import tic_tac_toe.domain.move.MoveResponse;
import tic_tac_toe.rest.tools.common.Fields.MoveMake;

import static org.hamcrest.Matchers.is;

@RequiredArgsConstructor(staticName = "of")
public class MoveResponseSpec extends ResponseSpec {
    private final MoveResponse expected;

    @Override
    protected void applySpec(ResponseSpecBuilder specBuilder, @Nullable String root) {
        specBuilder
                .expectBody(MoveMake.STATUS, is(expected.getStatus().name().toLowerCase()))
                .expectBody(MoveMake.IS_GAME_FINISHED, is(expected.isGameFinished()));
    }
}
