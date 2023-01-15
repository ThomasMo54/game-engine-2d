package com.motompro.gameengine2d.display;

import com.motompro.gameengine2d.manager.InputManager;
import com.motompro.gameengine2d.maths.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Display {

    private static final String DEFAULT_TITLE = "Display";
    private static final Dimension DEFAULT_DIMENSION = new Dimension(1280, 720);

    private final JFrame frame;
    private final Canvas canvas;
    private final InputManager inputManager;

    private boolean closed = false;

    public Display() {
        this.frame = new JFrame();
        frame.setTitle(DEFAULT_TITLE);
        frame.setSize(DEFAULT_DIMENSION);
        frame.setLocationRelativeTo(null);
        this.canvas = new Canvas();
        frame.setContentPane(canvas.getPanel());
        this.inputManager = new InputManager();
        frame.addKeyListener(inputManager);
        frame.addMouseListener(inputManager);
        frame.addMouseMotionListener(inputManager);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                e.getWindow().dispose();
                closed = true;
            }
        });
    }

    public void close() {
        frame.dispose();
        closed = true;
    }

    public void show() {
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(true);
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean isVisible() {
        return frame.isVisible();
    }

    public String getTitle() {
        return frame.getTitle();
    }

    public void setTitle(String title) {
        frame.setTitle(title);
    }

    public Vector2 getSize() {
        Dimension size = frame.getSize();
        return new Vector2(size.getWidth(), size.getHeight());
    }

    public void setSize(Vector2 size) {
        frame.setSize((int) size.getX(), (int) size.getY());
    }

    public Vector2 getLocation() {
        Point location = frame.getLocation();
        return new Vector2(location.getX(), location.getY());
    }

    public void setLocation(Vector2 location) {
        frame.setLocation((int) location.getX(), (int) location.getY());
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public InputManager getInputManager() {
        return inputManager;
    }
}
