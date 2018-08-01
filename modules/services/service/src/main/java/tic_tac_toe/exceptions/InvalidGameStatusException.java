package tic_tac_toe.exceptions;

import tic_tac_toe.exceptions.type.TypedException;

public class InvalidGameStatusException extends TypedException {

    public InvalidGameStatusException(String type, String message) {
        super(type, message);
    }
}
