package tic_tac_toe.spec;

import org.springframework.data.jpa.domain.Specification;
import tic_tac_toe.entity.Game;
import tic_tac_toe.entity.Game_;
import tic_tac_toe.spec.common.Spec;

public abstract class GameSpec {

    public static Specification<Game> fetchCrossPlayer() {
        return Spec.fetchLeft(Game_.crossPlayer);
    }

    public static Specification<Game> fetchNaughtPlayer() {
        return Spec.fetchLeft(Game_.naughtPlayer);
    }

    public static Specification<Game> fetchMoves() {
        return Spec.fetchLeft(Game_.moves);
    }

    public static Specification<Game> idIs(Long id) {
        return Spec.fieldIs(Game_.id, id);
    }
}
