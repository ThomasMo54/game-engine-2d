package com.motompro.gameengine2d.gameobject.component.exception;

public class NotRemovableComponentException extends RuntimeException {

    public NotRemovableComponentException(String errorMessage) {
        super(errorMessage);
    }
}
