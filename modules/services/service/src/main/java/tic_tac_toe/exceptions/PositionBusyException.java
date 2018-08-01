package tic_tac_toe.exceptions;

import tic_tac_toe.exceptions.type.TypedException;

public class PositionBusyException extends TypedException {
    public static final String MESSAGE = "Position (%d, %d) is busy";

    public PositionBusyException(int hPosition, int vPosition) {
        super(ErrorType.POSITION_BUSY, String.format(MESSAGE, hPosition, vPosition));
    }
}
