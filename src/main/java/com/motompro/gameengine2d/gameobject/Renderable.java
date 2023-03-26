package com.motompro.gameengine2d.gameobject;

import com.motompro.gameengine2d.display.Canvas;

public interface Renderable {

    void render(Canvas canvas);

    int getZIndex();
}
