package ru.smartbudjet.crm_for_catering.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * ДТО Ошибка.
 */
@Data
@AllArgsConstructor
public class ErrorResponse {

    @Schema(description = "Ошибка")
    private String error;

    @Schema(description = "Описание ошибки")
    private String errorDescription;
}