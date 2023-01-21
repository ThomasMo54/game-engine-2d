package com.motompro.gameengine2d.gameobject;

import com.motompro.gameengine2d.exception.NotRemovableComponentException;
import com.motompro.gameengine2d.gameobject.component.Component;
import com.motompro.gameengine2d.gameobject.component.TransformComponent;
import com.motompro.gameengine2d.math.Vector2;

public class Camera extends GameObject {

    private final TransformComponent transformComponent;
    private Vector2 viewportSize;

    public Camera(Vector2 viewportSize) {
        this.transformComponent = new TransformComponent();
        this.viewportSize = viewportSize;
        addComponent(transformComponent);
    }

    /**
     * Get the size of the part of the world the camera will show.
     * @return The size
     */
    public Vector2 getViewportSize() {
        return viewportSize;
    }

    /**
     * Set the size of the part of the world the camera will show.
     * @param viewportSize The size
     */
    public void setViewportSize(Vector2 viewportSize) {
        if(viewportSize == null)
            throw new IllegalArgumentException("viewportSize can't be null");
        this.viewportSize = viewportSize;
    }

    /**
     * Convert a position on the screen to the corresponding absolute position in the world.
     * @param screenPosition The screen position
     * @return A {@link Vector2} corresponding to the converted position
     */
    public Vector2 getScreenToWorldPosition(Vector2 screenPosition) {
        return transformComponent.getPosition().copy().add(screenPosition);
    }

    /**
     * Convert an absolute position in the world to the corresponding position on the screen.
     * @param worldPosition The screen position
     * @return A {@link Vector2} corresponding to the converted position
     */
    public Vector2 getWorldToScreenPosition(Vector2 worldPosition) {
        return worldPosition.copy().subtract(transformComponent.getPosition());
    }

    @Override
    public void removeComponent(Class<? extends Component> componentClass) {
        if(componentClass.equals(TransformComponent.class))
            throw new NotRemovableComponentException("the transform component can't be removed from a camera");
        super.removeComponent(componentClass);
    }
}
