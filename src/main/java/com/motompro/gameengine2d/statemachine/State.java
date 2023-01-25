package com.motompro.gameengine2d.statemachine;

public abstract class State {

    public abstract void onEnter();

    public abstract void onExit();

    public abstract void update(double deltaTime);

    public abstract void render();
}
