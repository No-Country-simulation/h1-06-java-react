package io.justina.h106javareact.adapters.exceptions;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiException> handleInvalidArgument(
            MethodArgumentNotValidException exception) {
        List<String> errorMap = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
        { errorMap.add(error.getField() + ": " + error.getDefaultMessage()); });
        ApiException apiException = new ApiException(errorMap);
        return ResponseEntity.badRequest().body(apiException);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ApiException> handleNotFoundException(
            EntityNotFoundException exception) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        List<String> errorMap = new ArrayList<>();
        errorMap.add(exception.getMessage());
        ApiException apiException = new ApiException(errorMap);
        return ResponseEntity.status(notFound).body(apiException);
    }

    @ExceptionHandler(value = {EntityExistsException.class})
    public ResponseEntity<ApiException> handleEntityExistsException(
            EntityExistsException exception) {

        HttpStatus entityExists = HttpStatus.CONFLICT;
        List<String> errorMap = new ArrayList<>();
        errorMap.add(exception.getMessage());
        ApiException apiException = new ApiException(errorMap);
        return ResponseEntity.status(entityExists).body(apiException);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ApiException> handleWrongArgumentException(
            IllegalArgumentException exception) {
        HttpStatus wrongArgument = HttpStatus.UNAUTHORIZED;
        List<String> errorMap = new ArrayList<>();
        errorMap.add(exception.getMessage());
        ApiException apiException = new ApiException(errorMap);
        return ResponseEntity.status(wrongArgument).body(apiException);
    }

    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity<ApiException> handleHttpClientErrorExceptions(
            HttpClientErrorException exception) {
        HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;
        List<String> errorMap = new ArrayList<>();
        errorMap.add(exception.getMessage());
        ApiException apiException = new ApiException(errorMap);
        return ResponseEntity.status(unauthorized).body(apiException);
    }

    @ExceptionHandler(value = AppointmentAvailabilityException.class)
    public ResponseEntity<ApiException> handleBadRequestExceptions(
            Exception exception) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        List<String> errorMap = new ArrayList<>();
        errorMap.add(exception.getMessage());
        ApiException apiException = new ApiException(errorMap);
        return ResponseEntity.status(badRequest).body(apiException);
    }

    //Maneja cualquier otra excepción que no haya sido considerada en los casos anteriores.
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiException> handleAllOtherExceptions(Exception exception) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        List<String> errorMap = new ArrayList<>();
        errorMap.add(exception.getMessage());
        ApiException apiException = new ApiException(errorMap);
        return ResponseEntity.status(internalServerError).body(apiException);
    };
}
