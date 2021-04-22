package com.pattern.recognition.controller.impl;

import com.pattern.recognition.exception.SpacePointAlreadyRegisteredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class RecognitionControllerAdvice {

    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<String> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        log.error("HttpMediaTypeNotSupportedException: {}", ex.getMessage());
        return new ResponseEntity<>(String.format("The received request body is not correct, error: %s", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {SpacePointAlreadyRegisteredException.class})
    public ResponseEntity<String> handleIndexOutOfBounds(SpacePointAlreadyRegisteredException ex) {
        log.error("SpacePointAlreadyRegisteredException: {}", ex.getMessage());
        return new ResponseEntity<>(String.format("Warning: %s", ex.getMessage()),
                HttpStatus.OK);
    }
}
