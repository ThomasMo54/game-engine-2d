package com.motompro.gameengine2d.gameobject;

import com.motompro.gameengine2d.gameobject.component.TransformComponent;
import com.motompro.gameengine2d.math.Vector2;

public class Camera extends GameObject {

    private Vector2 viewportSize;

    public Camera(Vector2 viewportSize) {
        this.viewportSize = viewportSize;
        addComponent(new TransformComponent());
    }

    public Vector2 getViewportSize() {
        return viewportSize;
    }

    public void setViewportSize(Vector2 viewportSize) {
        if(viewportSize == null)
            throw new IllegalArgumentException("viewportSize can't be null");
        this.viewportSize = viewportSize;
    }
}
