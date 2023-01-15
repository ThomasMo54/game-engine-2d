package com.motompro.gameengine2d;

import static org.junit.jupiter.api.Assertions.*;

import com.motompro.gameengine2d.maths.Vector2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Vector2Test {

    @Test
    @DisplayName("Vector addition")
    void testAdd() {
        Vector2 first = new Vector2(1, 1);
        Vector2 second = new Vector2(2, -6);
        Vector2 result = first.add(second);

        assertEquals(3, result.getX());
        assertEquals(-5, result.getY());
    }

    @Test
    @DisplayName("Vector subtraction")
    void testSubtract() {
        Vector2 first = new Vector2(1, 6);
        Vector2 second = new Vector2(2, 4);
        Vector2 result = first.subtract(second);

        assertEquals(-1, result.getX());
        assertEquals(2, result.getY());
    }

    @Test
    @DisplayName("Vector multiplication")
    void testMultiply() {
        Vector2 first = new Vector2(8, -1);
        Vector2 second = new Vector2(2, 4);
        Vector2 result = first.multiply(second);

        assertEquals(16, result.getX());
        assertEquals(-4, result.getY());
    }

    @Test
    @DisplayName("Vector division")
    void testDivide() {
        Vector2 first = new Vector2(1, 8);
        Vector2 second = new Vector2(2, 4);
        Vector2 result = first.divide(second);

        assertEquals(0.5, result.getX());
        assertEquals(2, result.getY());
    }

    @Test
    @DisplayName("Vector length")
    void testLength() {
        Vector2 vector1 = new Vector2(2, 2);
        Vector2 vector2 = new Vector2(-3, 4);

        assertEquals(Math.sqrt(8), vector1.length());
        assertEquals(5, vector2.length());
    }

    @Test
    @DisplayName("Vector normalization")
    void testNormalize() {
        Vector2 vector = new Vector2(8, 0);
        vector.normalize();

        assertEquals(1, vector.getX());
        assertEquals(0, vector.getY());
    }

    @Test
    @DisplayName("Vector dot product")
    void testDot() {
        Vector2 first = new Vector2(-1, 5);
        Vector2 second = new Vector2(3, 4);

        assertEquals(17, first.dot(second));
    }

    @Test
    @DisplayName("Vector angle")
    void testAngle() {
        Vector2 first = new Vector2(1, 0);
        Vector2 second = new Vector2(0, -1);

        assertEquals(Math.PI / 2, first.angle(second));

        first = new Vector2(1, 0);
        second = new Vector2(-1, 0);

        assertEquals(0, first.angle(second));
    }
}
