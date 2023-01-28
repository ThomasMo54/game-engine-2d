package com.motompro.gameengine2d.display.widget;

import com.motompro.gameengine2d.display.Display;
import com.motompro.gameengine2d.display.HUD;
import com.motompro.gameengine2d.manager.InputManager;
import com.motompro.gameengine2d.math.Vector2;

import java.awt.*;

public class Button extends Widget {

    private Runnable action = () -> {};
    private boolean mouseInside = false;
    private boolean mouseClicked = false;

    public Button(Display display) {
        super(display);
    }

    public void setAction(Runnable action) {
        this.action = action;
    }

    @Override
    public void update() {
        InputManager inputManager = display.getInputManager();
        if(mouseClicked && mouseInside && !inputManager.isLeftMouseButtonPressed())
            action.run();
        mouseClicked = mouseInside && inputManager.isLeftMouseButtonPressed();
        mouseInside = isMouseInside(inputManager);
    }

    private boolean isMouseInside(InputManager inputManager) {
        Vector2 mousePosition = inputManager.getMousePosition();
        Vector2 scaleRatio = display.getCanvasSize().copy().divide(display.getCamera().getViewportSize());
        return mousePosition.getX() >= (transformComponent.getPosition().getX() - transformComponent.getSize().getX() / 2) * scaleRatio.getX() &&
                mousePosition.getX() < (transformComponent.getPosition().getX() + transformComponent.getSize().getX() / 2) * scaleRatio.getX() &&
                mousePosition.getY() >= (transformComponent.getPosition().getY() - transformComponent.getSize().getY() / 2) * scaleRatio.getY() &&
                mousePosition.getY() < (transformComponent.getPosition().getY() + transformComponent.getSize().getY() / 2) * scaleRatio.getY();
    }

    @Override
    public void render(HUD hud) {
        hud.fillRect(transformComponent.getPosition(), transformComponent.getSize(), Color.WHITE);
    }
}
