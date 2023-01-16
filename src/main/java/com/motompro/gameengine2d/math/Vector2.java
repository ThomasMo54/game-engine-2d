package com.motompro.gameengine2d.math;

public class Vector2 {

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

    public Vector2 divide(double x, double y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public Vector2 divide(Vector2 vector) {
        return this.divide(vector.getX(), vector.getY());
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

    public double dot(Vector2 vector) {
        return x * vector.getX() + y * vector.getY();
    }

    public double angle(Vector2 vector) {
        return Math.acos(dot(vector) / (length() * vector.length())) % Math.PI;
    }

    public Vector2 copy() {
        return new Vector2(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static Vector2 of(double x, double y) {
        return new Vector2(x, y);
    }
}
