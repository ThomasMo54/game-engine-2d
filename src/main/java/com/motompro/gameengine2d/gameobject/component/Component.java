package com.motompro.gameengine2d.gameobject.component;

import com.motompro.gameengine2d.gameobject.component.exception.MissingComponentException;
import com.motompro.gameengine2d.gameobject.GameObject;

import java.util.List;

public abstract class Component {

    protected GameObject gameObject;

    /**
     * Get the game object this component is attached to.
     * @return The {@link GameObject}
     */
    public GameObject getGameObject() {
        return gameObject;
    }

    /**
     * Set the game object this component is attached to. This method is called automatically on {@link GameObject#addComponent(Component)}
     * call so you should <b>never</b> call this method by yourself.
     * @param gameObject The game object this component is attached to
     */
    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    /**
     * Get the list of the components that are required on a game object to make this component work. A {@link MissingComponentException}
     * will be thrown if one of these components is missing.
     * @return The {@link List} of required components
     */
    public abstract List<Class<? extends Component>> getComponentRequirements();

    /**
     * Get if the component must be unique on a game object.
     * @return A boolean
     */
    public abstract boolean isUnique();
}
