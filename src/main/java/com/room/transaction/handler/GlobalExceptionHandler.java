package com.room.transaction.handler;


import com.room.transaction.config.AppConfig;
import com.room.transaction.constants.GeneralConstants;
import com.room.transaction.dto.MetaResult;
import com.room.transaction.util.MetaResultFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    final MetaResultFactory metaResultFactory;

    public GlobalExceptionHandler(AppConfig appConfig, MetaResultFactory metaResultFactory) {
        this.metaResultFactory = metaResultFactory;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<MetaResult> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(objectError -> ((FieldError) objectError).getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<MetaResult>(metaResultFactory.create("input validated failed", "input_failed", errors), HttpStatus.BAD_REQUEST);
    }
}
