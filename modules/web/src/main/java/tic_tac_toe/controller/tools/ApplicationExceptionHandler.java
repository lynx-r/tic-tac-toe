package tic_tac_toe.controller.tools;

import com.fasterxml.jackson.databind.JsonMappingException;
import java.beans.Introspector;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import tic_tac_toe.exceptions.NoSuchGameException;
import tic_tac_toe.exceptions.SameSecondPlayerException;
import tic_tac_toe.exceptions.type.TypedError;
import tic_tac_toe.exceptions.type.TypedErrorImpl;
import tic_tac_toe.exceptions.type.TypedException;

@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(NoSuchGameException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public TypedError handleNoSuchGameException(NoSuchGameException ex) { return ex; }

    @ExceptionHandler(SameSecondPlayerException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public TypedError handleSameSecondPlayerException(SameSecondPlayerException ex) { return ex; }

    /**
     * Validation violation
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TypedError handleValidationException(ConstraintViolationException ex, HttpServletResponse response) {
        String message = ex.getConstraintViolations().stream().
                filter(Objects::nonNull).
                map((violation) -> violation.getPropertyPath() + ": " + violation.getMessage()).
                collect(Collectors.joining(", "));
        return new TypedErrorImpl("validationException", message);
    }

    /**
     * Controller argument conversion exceptions
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public TypedError handleConversionFailedException(MethodArgumentTypeMismatchException ex) {
        return new TypedErrorImpl(Introspector.decapitalize(ex.getClass().getSimpleName()),
                String.format("Bad value - %s for parameter - %s", ex.getValue(), ex.getName()));
    }

    /**
     * Input exceptions
     */
    @ExceptionHandler(JsonMappingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected TypedError handleInputExceptions(Exception ex) {
        return new TypedErrorImpl(ex);
    }

    /**
     * Request body syntax error
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected TypedError handleInvalidRequestBody(HttpMessageNotReadableException ex) {
        return new TypedErrorImpl(ex);
    }

    /**
     * All business exceptions
     */
    @ExceptionHandler(TypedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected TypedError handleTypedException(TypedException ex) {
        log.info("Business exception. Type: {}. Message: {}", ex.getErrorName(), ex.getMessage());
        return ex;
    }

    /**
     * All uncaught exceptions
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected TypedError handleAllOtherExceptions(Exception ex) {
        log.error("Unhandled exception", ex);
        return new TypedErrorImpl("unhandled", ex.getMessage());
    }
}
