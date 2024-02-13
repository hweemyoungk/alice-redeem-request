package cards.alice.redeemrequest.web.controllers;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class RedeemRequestErrorHandler {
    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity handleResourceNotFound(NoSuchElementException e) {
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity handleBindErrors(MethodArgumentNotValidException e) {
        List<Map<String, String>> errorList = e.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return errorMap;
                }).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errorList);
    }

/*
    @ExceptionHandler(TransactionSystemException.class)
    ResponseEntity handleJpaViolations(TransactionSystemException e) {
        ResponseEntity.BodyBuilder badRequestBuilder = ResponseEntity.badRequest();
        return badRequestBuilder.build();
    }
*/

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity handleJpaViolations(DataIntegrityViolationException e) {
        ResponseEntity.BodyBuilder badRequestBuilder = ResponseEntity.badRequest();
        return badRequestBuilder.build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity handleBeanValidationViolations(ConstraintViolationException e) {
        List<Map<String, String>> errorList = e.getConstraintViolations().stream()
                .map(constraintViolation -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(
                            constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getMessage()
                    );
                    return errorMap;
                }).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errorList);
    }
}
