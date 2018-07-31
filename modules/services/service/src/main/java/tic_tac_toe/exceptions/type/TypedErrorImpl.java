package tic_tac_toe.exceptions.type;

import java.beans.Introspector;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TypedErrorImpl implements TypedError, Serializable {

    private static final long serialVersionUID = -3322672866466009075L;

    private final String errorName;
    private final String userMessage;

    public TypedErrorImpl(Throwable throwable) {
        this.errorName = buildTypeByClass(throwable.getClass());
        this.userMessage = throwable.getMessage();
    }

    TypedErrorImpl(Class<?> errorClass, String userMessage) {
        this.errorName = buildTypeByClass(errorClass);
        this.userMessage = userMessage;
    }

    private String buildTypeByClass(Class<?> errorClass) {
        return Introspector.decapitalize(errorClass.getSimpleName());
    }
}
