package ru.smartbudjet.crm_for_catering.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ru.smartbudjet.crm_for_catering.exceptions.ForbiddenException;
import ru.smartbudjet.crm_for_catering.exceptions.UnsupportedMethodException;
import ru.smartbudjet.crm_for_catering.exceptions.ValidationException;
import ru.smartbudjet.crm_for_catering.model.dto.ErrorResponse;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * Обработчик исключений.
 */
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdviceHandler {

    /**
     * Сущность не найдена.
     *
     * @param e - исключение
     * @return ответ
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(final EntityNotFoundException e) {
        log.atError()
                .log(e.getMessage());
        final ErrorResponse response = new ErrorResponse("Сущность не найдена", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    /**
     * Сущность не найдена.
     *
     * @param e - исключение
     * @return ответ
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleException(final ValidationException e) {
        log.atError()
                .log(e.getMessage());
        final ErrorResponse response = new ErrorResponse("Ошибка валидации", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    /**
     * Вызван не поддерживаемый метод.
     *
     * @param e исключение
     * @return красивый ответ
     */
    @ExceptionHandler(UnsupportedMethodException.class)
    public ResponseEntity<ErrorResponse> handleException(final UnsupportedMethodException e) {
        log.atError()
                .log(e.getMessage());
        final ErrorResponse response = new ErrorResponse("Вызван неподдеживаемый метод", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    /**
     * Ошибка валидации ДТО.
     *
     * @param e исключение
     * @return красивый ответ
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(final MethodArgumentNotValidException e) {
        log.atError()
                .log(e.getMessage());
        final Map<String, String> errors = new ConcurrentHashMap<>();
        e.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    final String fieldName = ((FieldError) error).getField();
                    final String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    /**
     * Исключение ограничений значения полей при валидации входящих сообщений.
     *
     * @param e исключение
     * @return красивый ответ
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleException(final ConstraintViolationException e) {
        log.atError()
                .log(e.getMessage());
        final ErrorResponse response = new ErrorResponse("Ошибка валидации", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    /**
     * Исключение для валидации на изменение.
     *
     * @param e исключение
     * @return красивый ответ
     */
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleException(final ForbiddenException e) {
        log.atError()
                .log(e.getMessage());
        final ErrorResponse response = new ErrorResponse("Запрещено", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }


}