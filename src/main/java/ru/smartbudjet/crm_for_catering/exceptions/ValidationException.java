package ru.smartbudjet.crm_for_catering.exceptions;

import java.io.Serial;


/**
 * Ошибка валидации полей.
 */
public class ValidationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;


    public ValidationException(final String message) {
        super(message);
    }


    public ValidationException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
