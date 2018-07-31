package tic_tac_toe.exceptions;

import lombok.Getter;
import tic_tac_toe.exceptions.type.TypedException;

@Getter
public class SameSecondPlayerException extends TypedException {

    public SameSecondPlayerException(String login) {
        super(ErrorType.THE_SAME_SECOND_PLAYER, String.format("The second player is the same as first: %s", login));
    }
}
