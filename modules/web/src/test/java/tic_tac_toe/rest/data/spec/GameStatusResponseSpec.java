package tic_tac_toe.rest.data.spec;

import io.restassured.builder.ResponseSpecBuilder;
import javax.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import tic_tac_toe.domain.game.GameStatusResponse;
import tic_tac_toe.enums.GameResult;
import tic_tac_toe.rest.tools.common.Fields.Status;

import static org.hamcrest.Matchers.is;

@RequiredArgsConstructor(staticName = "of")
public class GameStatusResponseSpec extends ResponseSpec {
    private final GameStatusResponse result;

    @Override
    protected void applySpec(ResponseSpecBuilder specBuilder, @Nullable String root) {
        String gameResult = getExpectedGameResult(result.getResult());
        specBuilder.
                expectBody(Status.RESULT, is(gameResult));
    }

    private String getExpectedGameResult(GameResult result) {
        switch (result) {
            case CROSS_WON:
                return "crossWin";
            case NAUGHT_WON:
                return "naughtWin";
            case DRAW:
                return "draw";
        }
        return "null";
    }
}
