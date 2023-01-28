package com.motompro.gameengine2d.display.widget;

import com.motompro.gameengine2d.display.Display;
import com.motompro.gameengine2d.display.HUD;
import com.motompro.gameengine2d.gameobject.GameObject;
import com.motompro.gameengine2d.gameobject.component.TransformComponent;

public abstract class Widget extends GameObject {

    protected final Display display;
    protected final TransformComponent transformComponent;

    public Widget(Display display) {
        this.display = display;
        this.transformComponent = new TransformComponent();
        addComponent(transformComponent);
    }

    public abstract void update();

    public abstract void render(HUD hud);
}
