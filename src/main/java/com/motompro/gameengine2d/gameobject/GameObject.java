package com.motompro.gameengine2d.gameobject;

import com.motompro.gameengine2d.exception.MissingComponentException;
import com.motompro.gameengine2d.exception.UniqueComponentException;
import com.motompro.gameengine2d.gameobject.component.Component;

import java.util.*;

public class GameObject {

    private final UUID id;
    private final List<Component> components = new ArrayList<>();
    private final Set<Class<? extends Component>> componentsClass = new HashSet<>();

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
        return componentsClass.contains(componentClass);
    }

    public void removeComponent(Class<? extends Component> componentClass) {
        components.removeIf(component -> {
            boolean toRemove = component.getClass().equals(componentClass) || component.getComponentRequirements().contains(componentClass);
            if(toRemove)
                componentsClass.remove(component.getClass());
            return toRemove;
        });
    }

    public void addComponent(Component component) {
        if(component == null)
            throw new IllegalArgumentException("component can't be null");
        if(component.isUnique() && hasComponent(component.getClass()))
            throw new UniqueComponentException("This component type is already added to this game object");
        Optional<Class<? extends Component>> missingComponent = component.getComponentRequirements().stream().filter(clazz -> !hasComponent(clazz)).findFirst();
        if(missingComponent.isPresent())
            throw new MissingComponentException("Missing " + missingComponent.get().getSimpleName());
        components.add(component);
        componentsClass.add(component.getClass());
        component.setGameObject(this);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof GameObject other))
            return false;
        return id.equals(other.getId());
    }
}
