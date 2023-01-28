package com.motompro.gameengine2d.display;

import com.motompro.gameengine2d.math.Vector2;
import com.motompro.gameengine2d.math.shape.Polygon;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Canvas {

    protected Graphics2D graphics;
    protected AffineTransform transform;

    protected void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
        this.transform = graphics.getTransform();
    }

    public void drawLine(Vector2 position1, Vector2 position2, Color color) {
        graphics.setColor(color);
        graphics.drawLine((int) position1.getX(), (int) position1.getY(), (int) position2.getX(), (int) position2.getY());
    }

    public void drawRect(Vector2 position, Vector2 size, Color color) {
        graphics.setColor(color);
        graphics.drawRect((int) (position.getX() - size.getX() / 2), (int) (position.getY() - size.getY() / 2), (int) size.getX(), (int) size.getY());
    }

    public void drawRect(Vector2 position, Vector2 size, Color color, double angle) {
        graphics.setColor(color);
        graphics.rotate(angle, (int) position.getX(), (int) position.getY());
        graphics.drawRect((int) (position.getX() - size.getX() / 2), (int) (position.getY() - size.getY() / 2), (int) size.getX(), (int) size.getY());
        graphics.setTransform(transform);
    }

    public void fillRect(Vector2 position, Vector2 size, Color color) {
        graphics.setColor(color);
        graphics.fillRect((int) (position.getX() - size.getX() / 2), (int) (position.getY() - size.getY() / 2), (int) size.getX(), (int) size.getY());
    }

    public void fillRect(Vector2 position, Vector2 size, Color color, double angle) {
        graphics.setColor(color);
        graphics.rotate(angle, (int) position.getX(), (int) position.getY());
        graphics.fillRect((int) (position.getX() - size.getX() / 2), (int) (position.getY() - size.getY() / 2), (int) size.getX(), (int) size.getY());
        graphics.setTransform(transform);
    }

    public void clearRect(Vector2 position, Vector2 size) {
        graphics.clearRect((int) (position.getX() - size.getX() / 2), (int) (position.getY() - size.getY() / 2), (int) size.getX(), (int) size.getY());
    }

    public void clearRect(Vector2 position, Vector2 size, double angle) {
        graphics.rotate(angle, (int) position.getX(), (int) position.getY());
        graphics.clearRect((int) (position.getX() - size.getX() / 2), (int) (position.getY() - size.getY() / 2), (int) size.getX(), (int) size.getY());
        graphics.setTransform(transform);
    }

    public void drawPolygon(Vector2 position, Polygon polygon, Color color) {
        int[][] points = getPolygonPoints(position, polygon);
        graphics.setColor(color);
        graphics.drawPolygon(points[0], points[1], polygon.getVertices().size());
    }

    public void drawPolygon(Vector2 position, Polygon polygon, Color color, double angle) {
        int[][] points = getPolygonPoints(position, polygon);
        graphics.setColor(color);
        graphics.rotate(angle, (int) position.getX(), (int) position.getY());
        graphics.drawPolygon(points[0], points[1], polygon.getVertices().size());
        graphics.setTransform(transform);
    }

    public void fillPolygon(Vector2 position, Polygon polygon, Color color) {
        int[][] points = getPolygonPoints(position, polygon);
        graphics.setColor(color);
        graphics.fillPolygon(points[0], points[1], polygon.getVertices().size());
    }

    public void fillPolygon(Vector2 position, Polygon polygon, Color color, double angle) {
        int[][] points = getPolygonPoints(position, polygon);
        graphics.setColor(color);
        graphics.rotate(angle, (int) position.getX(), (int) position.getY());
        graphics.fillPolygon(points[0], points[1], polygon.getVertices().size());
        graphics.setTransform(transform);
    }

    private int[][] getPolygonPoints(Vector2 position, Polygon polygon) {
        int nPoints = polygon.getVertices().size();
        int[] xPoints = new int[nPoints];
        int[] yPoints = new int[nPoints];
        for(int i = 0; i < nPoints; i++) {
            Vector2 vertice = polygon.getVertices().get(i);
            xPoints[i] = (int) (position.getX() + vertice.getX());
            yPoints[i] = (int) (position.getY() + vertice.getY());
        }
        return new int[][] { xPoints, yPoints };
    }

    public void drawOval(Vector2 position, Vector2 size, Color color) {
        graphics.setColor(color);
        graphics.drawOval((int) position.getX(), (int) position.getY(), (int) size.getX(), (int) size.getY());
    }

    public void drawOval(Vector2 position, Vector2 size, Color color, double angle) {
        graphics.setColor(color);
        graphics.rotate(angle, (int) position.getX(), (int) position.getY());
        graphics.drawOval((int) (position.getX() - size.getX() / 2), (int) (position.getY() - size.getY() / 2), (int) size.getX(), (int) size.getY());
        graphics.setTransform(transform);
    }

    public void fillOval(Vector2 position, Vector2 size, Color color) {
        graphics.setColor(color);
        graphics.fillOval((int) (position.getX() - size.getX() / 2), (int) (position.getY() - size.getY() / 2), (int) size.getX(), (int) size.getY());
    }

    public void fillOval(Vector2 position, Vector2 size, Color color, double angle) {
        graphics.setColor(color);
        graphics.rotate(angle, (int) position.getX(), (int) position.getY());
        graphics.fillOval((int) (position.getX() - size.getX() / 2), (int) (position.getY() - size.getY() / 2), (int) size.getX(), (int) size.getY());
        graphics.setTransform(transform);
    }

    public void drawText(Vector2 position, Font font, Color color, String text) {
        graphics.setColor(color);
        graphics.setFont(font);
        graphics.drawString(text, (float) position.getY(), (float) position.getY());
    }

    public void drawText(Vector2 position, Font font, Color color, String text, double angle) {
        graphics.setColor(color);
        graphics.setFont(font);
        int tx = (int) (position.getX() + graphics.getFontMetrics().stringWidth(text) / 2);
        int ty = (int) (position.getY() + font.getSize() / 2);
        graphics.rotate(angle, tx, ty);
        graphics.drawString(text, (float) position.getY(), (float) position.getY());
        graphics.setTransform(transform);
    }

    public void drawImage(Image image, Vector2 position) {
        graphics.drawImage(image, (int) position.getX(), (int) position.getY(), null);
    }

    public void drawImage(Image image, Vector2 position, double angle) {
        graphics.rotate(angle, (int) position.getX(), (int) position.getY());
        graphics.drawImage(image, (int) (position.getX() - image.getWidth(null) / 2), (int) (position.getY() - image.getHeight(null) / 2), null);
        graphics.setTransform(transform);
    }
}
