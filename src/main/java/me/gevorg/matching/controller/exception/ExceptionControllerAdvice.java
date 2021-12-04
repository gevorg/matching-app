package me.gevorg.matching.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * We just hide all exceptions and throw minimalistic error message for UI.
 */
@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {
    /**
     * Handler for all exceptions.
     *
     * @param ex exception from other layers.
     * @return response with minimal message.
     */
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        log.error("Failed to process request", ex);

        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
