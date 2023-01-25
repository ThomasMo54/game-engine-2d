package com.motompro.gameengine2d.statemachine;

import java.util.HashMap;
import java.util.Map;

public class StateMachine {

    private final Map<String, State> states = new HashMap<>();
    private State activeState;

    public void addTransition(String transition, State state) {
        if(state == null)
            throw new IllegalArgumentException("state can't be null");
        states.put(transition, state);
    }

    public void removeTransition(String transition) {
        states.remove(transition);
    }

    public void changeState(String transition) {
        State state = states.get(transition);
        if(state == null)
            throw new IllegalArgumentException("transition \"" + transition + "\" does not exist");
        activeState.onExit();
        this.activeState = state;
        activeState.onEnter();
    }

    public void setDefaultState(State state) {
        this.activeState = state;
    }

    public void update(double deltaTime) {
        if(activeState == null)
            return;
        activeState.update(deltaTime);
    }

    public void render() {
        if(activeState == null)
            return;
        activeState.render();
    }
}
