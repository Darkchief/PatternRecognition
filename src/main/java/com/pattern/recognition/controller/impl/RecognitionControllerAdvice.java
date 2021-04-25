package com.pattern.recognition.controller.impl;

import com.pattern.recognition.exception.NotEnoughPointsException;
import com.pattern.recognition.exception.NotEnoughPointsRegisteredException;
import com.pattern.recognition.exception.PointAlreadyRegisteredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This is the controller advice, it will be used for errors handling
 */
@Slf4j
@ControllerAdvice
public class RecognitionControllerAdvice {

    /**
     * In case the pointRequest is not correct, we catch the HttpMediaTypeNotSupportedException
     *
     * @param ex HttpMediaTypeNotSupportedException
     * @return BadRequest Response
     */
    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<String> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        log.error("HttpMediaTypeNotSupportedException: {}", ex.getMessage());
        return new ResponseEntity<>(String.format("The received request body is not correct, error: %s", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * In case the point inside pointRequest was already inside the plane,
     * we catch the PointAlreadyRegisteredException
     *
     * @param ex PointAlreadyRegisteredException
     * @return OK Response
     */
    @ExceptionHandler(value = {PointAlreadyRegisteredException.class})
    public ResponseEntity<String> handlePointAlreadyRegistered(PointAlreadyRegisteredException ex) {
        log.warn("PointAlreadyRegisteredException: {}", ex.getMessage());
        return new ResponseEntity<>(String.format("Warning: %s", ex.getMessage()),
                HttpStatus.OK);
    }

    /**
     * In case insisde the plane there aren't enough points to calculate a segment,
     * we catch the NotEnoughPointsRegisteredException
     *
     * @param ex NotEnoughPointsRegisteredException
     * @return Internal Server Error Response
     */
    @ExceptionHandler(value = {NotEnoughPointsRegisteredException.class})
    public ResponseEntity<String> handleNotEnoughPointsRegistered(NotEnoughPointsRegisteredException ex) {
        log.error("NotEnoughPointsRegisteredException: {}", ex.getMessage());
        return new ResponseEntity<>(String.format("Error: %s", ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * In case collinearPoints are not enough to calculate a segment, we catch the NotEnoughPointsException
     *
     * @param ex NotEnoughPointsException
     * @return BadRequest Response
     */
    @ExceptionHandler(value = {NotEnoughPointsException.class})
    public ResponseEntity<String> handleNotEnoughPoints(NotEnoughPointsException ex) {
        log.error("NotEnoughPointsException: {}", ex.getMessage());
        return new ResponseEntity<>(String.format("Error: %s", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
