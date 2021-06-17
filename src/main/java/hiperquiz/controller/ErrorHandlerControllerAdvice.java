package hiperquiz.controller;

import hiperquiz.entities.ErrorResponse;
import hiperquiz.entities.User;
import hiperquiz.exception.EntityAlreadyExistsException;
import hiperquiz.exception.EntityNotFoundException;
import hiperquiz.exception.EntityUpdateException;
import hiperquiz.exception.InvalidEntityDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice(basePackageClasses = UserController.class)
public class ErrorHandlerControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleEntityAlreadyExistsException(EntityAlreadyExistsException e){
        return ResponseEntity.status(BAD_REQUEST).body(new ErrorResponse(BAD_REQUEST.value(), e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleEntityNotFoundExistsException(EntityNotFoundException e){
        return ResponseEntity.status(NOT_FOUND).body(new ErrorResponse(NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleEntityConstraintViolations(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(
                new ErrorResponse(BAD_REQUEST.value(), ex.getMessage(),
                        ex.getBindingResult().getAllErrors().stream()
                                .map(err -> {
                                    if (err instanceof FieldError) {
                                        FieldError ferr = (FieldError) err;
                                        String message = String.format("'%s': %s",
                                                ferr.getField(), ferr.getDefaultMessage());
                                        if (ferr.getRejectedValue() != null && ferr.getRejectedValue().toString().length() > 0) {
                                            message += String.format(", invalid value: %s", ferr.getRejectedValue().toString());
                                        }
                                        return message;
                                    } else {
                                        return err.getDefaultMessage();
                                    }
                                }).collect(Collectors.toList()))
        );
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserUpdate(EntityUpdateException e){
        return ResponseEntity.status(500).body(new ErrorResponse(500, e.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleDbConstraintViolations(InvalidEntityDataException e) {
        return ResponseEntity.badRequest().body(
                new ErrorResponse(BAD_REQUEST.value(), e.getMessage())
        );
    }
}
