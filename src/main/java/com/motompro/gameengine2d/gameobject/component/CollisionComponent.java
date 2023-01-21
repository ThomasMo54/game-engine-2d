package com.motompro.gameengine2d.gameobject.component;

import com.motompro.gameengine2d.gameobject.GameObject;
import com.motompro.gameengine2d.math.Vector2;
import com.motompro.gameengine2d.util.shape.Circle;
import com.motompro.gameengine2d.util.shape.Polygon;
import com.motompro.gameengine2d.util.shape.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CollisionComponent extends Component {

    private Shape shape;

    public CollisionComponent(Shape shape) {
        setShape(shape);
    }

    public Shape getShape() {
        return shape;
    }

    /**
     * Set the shape used by the {@link #isColliding(GameObject)} method to detect a collision. It must be a {@link Circle}
     * or a {@link Polygon}. If the given shape is a {@link Polygon}, it must be convex for a proper collision detection.
     * @param shape The shape
     */
    public void setShape(Shape shape) {
        if(shape == null)
            throw new IllegalArgumentException("shape can't be null");
        if(!(shape instanceof Circle) && !(shape instanceof Polygon))
            throw new IllegalArgumentException("shape must be instance of Circle or Polygon");
        this.shape = shape;
    }

    /**
     * Check if this object and another given object are colliding with each other. This method only works with {@link Circle}
     * and <b>convex</b> {@link Polygon}. If a non-convex polygon is used the collision detection may be incorrect.
     * @param otherObject The other object that will be checked
     * @return A boolean set to true if collision, false otherwise.
     */
    public boolean isColliding(GameObject otherObject) {
        CollisionComponent otherCollision = otherObject.getComponent(CollisionComponent.class);
        if(!otherObject.hasComponent(CollisionComponent.class))
            return false;
        if(shape instanceof Polygon && otherCollision.getShape() instanceof Polygon)
            return isCollidingPolyToPoly(otherObject);
        if(shape instanceof Circle && otherCollision.getShape() instanceof Circle)
            return isCollidingCircleToCircle(otherObject);
        if(shape instanceof Polygon && otherCollision.getShape() instanceof Circle)
            return isCollidingPolyToCircle(gameObject, otherObject);
        return isCollidingPolyToCircle(otherObject, gameObject);
    }

    private boolean isCollidingPolyToPoly(GameObject otherObject) {
        TransformComponent transform = gameObject.getComponent(TransformComponent.class);
        TransformComponent otherTransform = otherObject.getComponent(TransformComponent.class);
        Polygon polygon = (Polygon) shape;
        Polygon otherPolygon = (Polygon) otherObject.getComponent(CollisionComponent.class).getShape();
        List<Vector2> vertices = polygon.getVertices().stream().map(vertice -> vertice.copy().add(transform.getPosition())).toList();
        List<Vector2> otherVertices = otherPolygon.getVertices().stream().map(vertice -> vertice.copy().add(otherTransform.getPosition())).toList();
        return Stream.concat(polygon.getNormalVectors().stream(), otherPolygon.getNormalVectors().stream()).allMatch(axis -> {
            Vector2 interval1 = getInterval(vertices, axis);
            Vector2 interval2 = getInterval(otherVertices, axis);
            return interval2.getX() <= interval1.getY() && interval1.getX() <= interval2.getY();
        });
    }

    private boolean isCollidingCircleToCircle(GameObject otherObject) {
        TransformComponent transform = gameObject.getComponent(TransformComponent.class);
        TransformComponent otherTransform = otherObject.getComponent(TransformComponent.class);
        double radiusSum = (((Circle) gameObject.getComponent(CollisionComponent.class).getShape()).getRadius() / 2 + ((Circle) otherObject.getComponent(CollisionComponent.class).getShape()).getRadius() / 2);
        return transform.getPosition().distanceSquared(otherTransform.getPosition()) <= radiusSum * radiusSum;
    }

    private boolean isCollidingPolyToCircle(GameObject polygonObject, GameObject circleObject) {
        TransformComponent polygonTransform = polygonObject.getComponent(TransformComponent.class);
        TransformComponent circleTransform = circleObject.getComponent(TransformComponent.class);
        Polygon polygon = (Polygon) polygonObject.getComponent(CollisionComponent.class).getShape();
        Circle circle = (Circle) circleObject.getComponent(CollisionComponent.class).getShape();
        Vector2 circleCenter = circleTransform.getPosition();
        double radius = circle.getRadius();

        List<Vector2> polygonVertices = polygon.getVertices().stream().map(vertice -> vertice.copy().add(polygonTransform.getPosition())).toList();
        double minDist = polygonVertices.get(0).distanceSquared(circleCenter);
        Vector2 circleAxis = circleCenter.copy().subtract(polygonVertices.get(0));
        for(int i = 1; i < polygonVertices.size(); i++) {
            double dist = polygonVertices.get(i).distanceSquared(circleCenter);
            if(dist < minDist) {
                minDist = dist;
                circleAxis = circleCenter.copy().subtract(polygonVertices.get(i));
            }
        }
        circleAxis.normalize();

        List<Vector2> axes = new ArrayList<>(polygon.getNormalVectors());
        axes.add(circleAxis);
        return axes.stream().allMatch(axis -> {
            Vector2 interval1 = getInterval(polygonVertices, axis);
            double centerProjection = circleCenter.dot(axis);
            return centerProjection - radius <= interval1.getY() && interval1.getX() <= centerProjection + radius;
        });
    }

    private Vector2 getInterval(List<Vector2> vertices, Vector2 axis) {
        Vector2 interval = Vector2.zero();

        interval.setX(vertices.get(0).dot(axis));
        interval.setY(interval.getX());
        for(int i = 1; i < vertices.size(); i++) {
            double projection = vertices.get(i).dot(axis);
            interval.set(Math.min(projection, interval.getX()), Math.max(projection, interval.getY()));
        }

        return interval;
    }

    @Override
    public List<Class<? extends Component>> getComponentRequirements() {
        return List.of(TransformComponent.class);
    }

    @Override
    public boolean isUnique() {
        return true;
    }
}
