package com.motompro.gameengine2d.gameobject.component;

import com.motompro.gameengine2d.gameobject.GameObject;

import java.util.List;

public abstract class Component {

    protected GameObject gameObject;

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public abstract List<Class<? extends Component>> getComponentRequirements();
}
