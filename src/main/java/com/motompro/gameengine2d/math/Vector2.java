package com.motompro.gameengine2d.math;

import java.util.Objects;

public class Vector2 {

    private static final double EPS = 0.00000001;

    private double x, y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Vector2 set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vector2 add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2 add(Vector2 vector) {
        return this.add(vector.getX(), vector.getY());
    }

    public Vector2 subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2 subtract(Vector2 vector) {
        return this.subtract(vector.getX(), vector.getY());
    }

    public Vector2 multiply(double x, double y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vector2 multiply(Vector2 vector) {
        return this.multiply(vector.getX(), vector.getY());
    }

    public Vector2 multiply(double v) {
        this.x *= v;
        this.y *= v;
        return this;
    }

    public Vector2 divide(double x, double y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vector2 divide(Vector2 vector) {
        return this.divide(vector.getX(), vector.getY());
    }

    public Vector2 divide(double v) {
        this.x /= v;
        this.y /= v;
        return this;
    }

    public double lengthSquared() {
        return x * x + y * y;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vector2 normalize() {
        double length = length();
        this.x /= length;
        this.y /= length;
        return this;
    }

    /**
     * Make this vector rotate by the given angle (counterclockwise).
     * @param angle An angle in radians
     * @return This {@link Vector2} after the rotation
     */
    public Vector2 rotate(double angle) {
        double newX = x * Math.cos(angle) - y * Math.sin(angle);
        double newY = x * Math.sin(angle) + y * Math.cos(angle);
        this.x = newX;
        this.y = newY;
        return this;
    }

    public double dot(Vector2 vector) {
        return x * vector.getX() + y * vector.getY();
    }

    /**
     * @param vector The other vector
     * @return The angle between two vectors (in radians)
     */
    public double angle(Vector2 vector) {
        return Math.acos(dot(vector) / (length() * vector.length())) % Math.PI;
    }

    /**
     * @param vector The other point
     * @return The squared distance separating the two points of coordinates described by this vector and the other.
     */
    public double distanceSquared(Vector2 vector) {
        double dx = vector.getX() - x;
        double dy = vector.getY() - y;
        return dx * dx + dy * dy;
    }

    /**
     * @param vector The other point
     * @return The distance separating the two points of coordinates described by this vector and the other.
     */
    public double distance(Vector2 vector) {
        return Math.sqrt(distanceSquared(vector));
    }

    /**
     * Make a full copy of this vector.
     * @return The copy
     */
    public Vector2 copy() {
        return new Vector2(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Vector2 other))
            return false;
        return other.getX() >= x - EPS && other.getX() <= x + EPS &&
                other.getY() >= y - EPS && other.getY() <= y + EPS;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * @return A new vector
     */
    public static Vector2 of(double x, double y) {
        return new Vector2(x, y);
    }

    /**
     * @return A new null vector (x = 0, y = 0)
     */
    public static Vector2 zero() {
        return new Vector2(0, 0);
    }
}
