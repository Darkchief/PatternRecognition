package com.pattern.recognition.exception;

public class SpacePointAlreadyRegisteredException extends RuntimeException {

    public SpacePointAlreadyRegisteredException(String errorMessage) {
        super(errorMessage);
    }

}
