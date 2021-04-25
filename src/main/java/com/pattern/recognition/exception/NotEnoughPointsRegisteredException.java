package com.pattern.recognition.exception;

public class NotEnoughPointsRegisteredException extends RuntimeException {

    public NotEnoughPointsRegisteredException(String errorMessage) {
        super(errorMessage);
    }

}
