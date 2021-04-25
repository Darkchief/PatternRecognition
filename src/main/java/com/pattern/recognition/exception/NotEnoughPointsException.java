package com.pattern.recognition.exception;

public class NotEnoughPointsException extends RuntimeException {

    public NotEnoughPointsException(String errorMessage) {
        super(errorMessage);
    }

}
