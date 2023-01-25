package com.motompro.gameengine2d.math.shape;

import com.motompro.gameengine2d.math.Vector2;

import java.util.List;

public class Rectangle extends Polygon {

    private Vector2 size;

    public Rectangle(Vector2 size) {
        super(List.of(
                Vector2.of(-size.getX() / 2, -size.getY() / 2),
                Vector2.of(size.getX() / 2, -size.getY() / 2),
                Vector2.of(size.getX() / 2, size.getY() / 2),
                Vector2.of(-size.getX() / 2, size.getY() / 2)
        ));
    }

    @Override
    public double getPerimeter() {
        return 2 * size.getX() + 2 * size.getY();
    }
}
