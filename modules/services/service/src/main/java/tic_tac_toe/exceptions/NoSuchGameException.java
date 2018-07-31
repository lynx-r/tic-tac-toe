package tic_tac_toe.exceptions;

import lombok.Getter;
import tic_tac_toe.exceptions.type.TypedException;

@Getter
public class NoSuchGameException extends TypedException {

    public NoSuchGameException(Long gameId) {
        super(ErrorType.NO_SUCH_GAME, String.format("No such game with ID=%d", gameId));
    }
}
