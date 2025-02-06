package ru.smartbudjet.crm_for_catering.exceptions;

import java.io.Serial;

/**
 * Класс исключения для запрещения.
 */
public class ForbiddenException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ForbiddenException(final String message) {
        super(message);
    }

    public ForbiddenException(final String massage, final Throwable cause) {
        super(massage, cause);
    }

}
