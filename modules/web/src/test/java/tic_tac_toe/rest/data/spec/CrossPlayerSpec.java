package tic_tac_toe.rest.data.spec;

import io.restassured.builder.ResponseSpecBuilder;
import javax.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import tic_tac_toe.domain.player.PlayerDto;
import tic_tac_toe.rest.tools.common.Fields.Player;

import static org.hamcrest.Matchers.is;

@RequiredArgsConstructor(staticName = "of")
public class CrossPlayerSpec extends ResponseSpec {
    private final PlayerDto playerDto;

    @Override
    protected void applySpec(ResponseSpecBuilder specBuilder, @Nullable String root) {
        if (playerDto.getId() != null) {
            specBuilder.expectBody(Player.ID, is(playerDto.getId().intValue()));
        }
        specBuilder.
                expectBody(Player.LOGIN, is(playerDto.getLogin()));
    }
}
