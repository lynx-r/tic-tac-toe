package tic_tac_toe.exceptions.type;

/**
 * Base type for all errors going outside.
 */
public interface TypedError {

    /**
     * @return Error type. Must be unique across application.
     */
    String getErrorName();

    /**
     * @return Error description for developers.
     */
    String getUserMessage();
}
