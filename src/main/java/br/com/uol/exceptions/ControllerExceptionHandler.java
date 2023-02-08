package br.com.uol.exceptions;

import br.com.uol.entities.ApiErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        var message = new ApiErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getLocalizedMessage(),
                request.getDescription(false));
        return new ResponseEntity<ApiErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorMessage> handleGlobalExceptionHandler(Exception ex, WebRequest request) {
        ApiErrorMessage message = new ApiErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<ApiErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiErrorMessage> handleBadRequestException(BadRequestException exception, WebRequest request) {
        ApiErrorMessage message = new ApiErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(SQLConstraintException.class)
    public ResponseEntity<ApiErrorMessage> handleDataIntegrityViolation(SQLConstraintException exception, WebRequest request) {
        var message = new ApiErrorMessage(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                new Date(),
                exception.getLocalizedMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}