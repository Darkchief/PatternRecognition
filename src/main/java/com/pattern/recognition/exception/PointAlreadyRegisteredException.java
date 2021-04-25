package com.pattern.recognition.exception;

public class PointAlreadyRegisteredException extends RuntimeException {

    public PointAlreadyRegisteredException(String errorMessage) {
        super(errorMessage);
    }

}
