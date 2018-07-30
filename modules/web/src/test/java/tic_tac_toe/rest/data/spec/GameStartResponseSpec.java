package tic_tac_toe.rest.data.spec;

import io.restassured.builder.ResponseSpecBuilder;
import javax.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import tic_tac_toe.domain.game.start.GameStartResponse;
import tic_tac_toe.rest.tools.common.Fields.GameStart;

import static org.hamcrest.Matchers.is;

@RequiredArgsConstructor(staticName = "of")
public class GameStartResponseSpec extends ResponseSpec {
    private final GameStartResponse response;

    @Override
    protected void applySpec(ResponseSpecBuilder specBuilder, @Nullable String root) {
        if (response.getGameId() != null) {
            specBuilder.expectBody(GameStart.GAME_ID, is(response.getGameId().intValue()));
        }
        specBuilder
                .expectBody(GameStart.IS_STARTED, is(response.isStarted()))
                .addResponseSpecification(
                        CrossPlayerSpec.of(response.getCrossPlayer()).withRoot(root, GameStart.CROSS_PLAYER));
        if (response.getNaughtPlayer() != null) {
            specBuilder
                    .addResponseSpecification(
                            CrossPlayerSpec.of(response.getNaughtPlayer()).withRoot(root, GameStart.NAUGHT_PLAYER));
        }
    }
}
