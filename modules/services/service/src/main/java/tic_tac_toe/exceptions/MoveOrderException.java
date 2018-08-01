package tic_tac_toe.exceptions;

import tic_tac_toe.exceptions.type.TypedException;

public class MoveOrderException extends TypedException {
    public MoveOrderException() {
        super(ErrorType.MOVE_ORDER, "Not your move");
    }
}
