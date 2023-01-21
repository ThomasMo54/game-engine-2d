package com.motompro.gameengine2d.gameobject.component;

import com.motompro.gameengine2d.math.Vector2;

import java.util.List;

public class TransformComponent extends Component {

    private Vector2 position = Vector2.zero();
    private Vector2 size = Vector2.of(1, 1);
    private double rotation = 0;

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        if(position == null)
            throw new IllegalArgumentException("position can't be null");;
        this.position = position;
    }

    public void move(Vector2 direction) {
        position.add(direction);
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        if(size == null)
            throw new IllegalArgumentException("size can't be null");
        this.size = size;
    }

    public void scale(Vector2 scale) {
        size.add(scale);
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public void rotate(double angle) {
        rotation = (rotation + angle) % (2 * Math.PI);
    }

    @Override
    public List<Class<? extends Component>> getComponentRequirements() {
        return List.of();
    }
}
