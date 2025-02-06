package ru.smartbudjet.crm_for_catering.exceptions;

import java.io.Serial;

/**
 * Исключение UnsupportedMethodException.
 */
public class UnsupportedMethodException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public UnsupportedMethodException(final String massage) {
        super(massage);
    }
}
