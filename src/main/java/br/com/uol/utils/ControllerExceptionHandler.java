package br.com.uol.utils;


import br.com.uol.entities.ApiErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        var message = new ApiErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getLocalizedMessage(),
                request.getDescription(false));
        return new ResponseEntity<ApiErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        ApiErrorMessage message = new ApiErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<ApiErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<ApiErrorMessage> badRequestException(HttpClientErrorException.BadRequest ex, WebRequest request) {
        ApiErrorMessage message = new ApiErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<ApiErrorMessage>(message, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler
//    public ResponseEntity<ApiErrorMessage> handleConstraintException(ConstraintViolationException exception, WebRequest request) {
//        String errorMessage = new ArrayList<>(exception.getConstraintViolations()).get(0).getMessage();
//        ApiErrorMessage message = new ApiErrorMessage(HttpStatus.PRECONDITION_FAILED.value(), new Date(), exception.getMessage(),request.getDescription(false));
//        return new ResponseEntity<ApiErrorMessage>(message,HttpStatus.PRECONDITION_FAILED);
//    }


}