package com.motompro.gameengine2d.gameobject;

import com.motompro.gameengine2d.exception.MissingComponentException;
import com.motompro.gameengine2d.gameobject.component.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class GameObject {

    private final UUID id;
    private final List<Component> components = new ArrayList<>();

    public GameObject() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public <C extends Component> C getComponent(Class<C> componentClass) {
        return (C) components.stream().filter(component -> component.getClass().equals(componentClass)).findFirst().orElse(null);
    }

    public boolean hasComponent(Class<? extends Component> componentClass) {
        return components.stream().anyMatch(component -> component.getClass().equals(componentClass));
    }

    public void removeComponent(Class<? extends Component> componentClass) {
        components.removeIf(component -> component.getClass().equals(componentClass));
    }

    public void addComponent(Component component) {
        Optional<Class<? extends Component>> missingComponent = component.getComponentRequirements().stream().filter(clazz -> !hasComponent(clazz)).findFirst();
        if(missingComponent.isPresent())
            throw new MissingComponentException("Missing " + missingComponent.get().getSimpleName());
        components.add(component);
        component.setGameObject(this);
    }
}
