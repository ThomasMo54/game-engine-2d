package com.motompro.gameengine2d.display;

import com.motompro.gameengine2d.math.Vector2;

import java.awt.*;

public class Canvas {

    private Graphics2D graphics;

    public void setGraphics(Graphics2D graphics) {
        this.graphics = graphics;
    }

    public void drawLine(Vector2 position1, Vector2 position2, Color color) {
        graphics.setColor(color);
        graphics.drawLine((int) position1.getX(), (int) position1.getY(), (int) position2.getX(), (int) position2.getY());
    }

    public void drawRect(Vector2 position, Vector2 size, Color color) {
        graphics.setColor(color);
        graphics.drawRect((int) position.getX(), (int) position.getY(), (int) size.getX(), (int) size.getY());
    }

    public void fillRect(Vector2 position, Vector2 size, Color color) {
        graphics.setColor(color);
        graphics.fillRect((int) position.getX(), (int) position.getY(), (int) size.getX(), (int) size.getY());
    }

    public void clearRect(Vector2 position, Vector2 size) {
        graphics.clearRect((int) position.getX(), (int) position.getY(), (int) size.getX(), (int) size.getY());
    }

    public void drawOval(Vector2 position, Vector2 size, Color color) {
        graphics.setColor(color);
        graphics.drawOval((int) position.getX(), (int) position.getY(), (int) size.getX(), (int) size.getY());
    }

    public void fillOval(Vector2 position, Vector2 size, Color color) {
        graphics.setColor(color);
        graphics.fillOval((int) position.getX(), (int) position.getY(), (int) size.getX(), (int) size.getY());
    }

    public void drawText(Vector2 position, Font font, Color color, String text) {
        graphics.setColor(color);
        graphics.setFont(font);
        graphics.drawString(text, (float) position.getY(), (float) position.getY());
    }

    public void drawImage(Image image, Vector2 position) {
        graphics.drawImage(image, (int) position.getX(), (int) position.getY(), null);
    }
}
