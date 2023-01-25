package com.motompro.gameengine2d.gameobject.component;

import static org.junit.jupiter.api.Assertions.*;

import com.motompro.gameengine2d.gameobject.GameObject;
import com.motompro.gameengine2d.math.Vector2;
import com.motompro.gameengine2d.math.shape.Circle;
import com.motompro.gameengine2d.math.shape.Rectangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CollisionComponentTest {

    // Circles
    private final GameObject circle1;
    private final GameObject circle2;
    // Polygons
    private final GameObject polygon1;
    private final GameObject polygon2;

    public CollisionComponentTest() {
        this.circle1 = new GameObject();
        circle1.addComponent(new TransformComponent());
        circle1.addComponent(new CollisionComponent(new Circle(10)));

        this.circle2 = new GameObject();
        circle2.addComponent(new TransformComponent());
        circle2.addComponent(new CollisionComponent(new Circle(10)));

        this.polygon1 = new GameObject();
        polygon1.addComponent(new TransformComponent());
        polygon1.addComponent(new CollisionComponent(new Rectangle(Vector2.of(20, 20))));

        this.polygon2 = new GameObject();
        polygon2.addComponent(new TransformComponent());
        Rectangle rectangle = new Rectangle(Vector2.of(20, 20));
        rectangle.setRotation(Math.PI / 4);
        polygon2.addComponent(new CollisionComponent(rectangle));
    }

    @Test
    @DisplayName("Circle to circle full collision")
    void testCircleToCircleFull() {
        circle1.getComponent(TransformComponent.class).setPosition(Vector2.of(0, 0));
        circle2.getComponent(TransformComponent.class).setPosition(Vector2.of(5, 0));

        assertTrue(circle1.getComponent(CollisionComponent.class).isColliding(circle2));
    }

    @Test
    @DisplayName("Circle to circle partial collision")
    void testCircleToCirclePartial() {
        circle1.getComponent(TransformComponent.class).setPosition(Vector2.of(0, 0));
        circle2.getComponent(TransformComponent.class).setPosition(Vector2.of(10, 0));

        assertTrue(circle1.getComponent(CollisionComponent.class).isColliding(circle2));
    }

    @Test
    @DisplayName("Circle to circle no collision")
    void testCircleToCircleNo() {
        circle1.getComponent(TransformComponent.class).setPosition(Vector2.of(0, 0));
        circle2.getComponent(TransformComponent.class).setPosition(Vector2.of(20, 0));

        assertFalse(circle1.getComponent(CollisionComponent.class).isColliding(circle2));
    }

    @Test
    @DisplayName("Polygon to polygon full collision")
    void testPolygonToPolygonFull() {
        polygon1.getComponent(TransformComponent.class).setPosition(Vector2.of(0, 0));
        polygon2.getComponent(TransformComponent.class).setPosition(Vector2.of(5, 0));

        assertTrue(polygon1.getComponent(CollisionComponent.class).isColliding(polygon2));
    }

    @Test
    @DisplayName("Polygon to polygon partial collision")
    void testPolygonToPolygonPartial() {
        polygon1.getComponent(TransformComponent.class).setPosition(Vector2.of(0, 0));
        polygon2.getComponent(TransformComponent.class).setPosition(Vector2.of(10 + Math.sqrt(200), 0));

        assertTrue(polygon1.getComponent(CollisionComponent.class).isColliding(polygon2));
    }

    @Test
    @DisplayName("Polygon to polygon no collision")
    void testPolygonToPolygonNo() {
        polygon1.getComponent(TransformComponent.class).setPosition(Vector2.of(0, 0));
        polygon2.getComponent(TransformComponent.class).setPosition(Vector2.of(30, 0));

        assertFalse(polygon1.getComponent(CollisionComponent.class).isColliding(polygon2));
    }

    @Test
    @DisplayName("Polygon to circle full collision")
    void testPolygonToCircleFull() {
        polygon1.getComponent(TransformComponent.class).setPosition(Vector2.of(0, 0));
        circle1.getComponent(TransformComponent.class).setPosition(Vector2.of(10, 0));

        assertTrue(polygon1.getComponent(CollisionComponent.class).isColliding(circle1));
    }

    @Test
    @DisplayName("Polygon to circle partial collision")
    void testPolygonToCirclePartial() {
        polygon1.getComponent(TransformComponent.class).setPosition(Vector2.of(0, 0));
        circle1.getComponent(TransformComponent.class).setPosition(Vector2.of(20, 0));

        assertTrue(polygon1.getComponent(CollisionComponent.class).isColliding(circle1));
    }

    @Test
    @DisplayName("Polygon to circle no collision")
    void testPolygonToCircleNo() {
        polygon1.getComponent(TransformComponent.class).setPosition(Vector2.of(0, 0));
        circle1.getComponent(TransformComponent.class).setPosition(Vector2.of(30, 0));

        assertFalse(polygon1.getComponent(CollisionComponent.class).isColliding(circle1));
    }
}
