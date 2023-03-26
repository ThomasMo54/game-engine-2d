package com.motompro.gameengine2d.gameobject.component.exception;

public class MissingComponentException extends RuntimeException {

    public MissingComponentException(String errorMessage) {
        super(errorMessage);
    }
}
