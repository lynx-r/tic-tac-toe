package tic_tac_toe.exceptions;

import tic_tac_toe.exceptions.type.TypedException;

public class NoBlankCellException extends TypedException {

    public NoBlankCellException(String type) {
        super(type, "No Blank cells on the field");
    }
}
