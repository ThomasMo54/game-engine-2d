package com.motompro.gameengine2d.display;

import com.motompro.gameengine2d.manager.InputManager;
import com.motompro.gameengine2d.math.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

public class Display {

    private static final String DEFAULT_TITLE = "Display";
    private static final Dimension DEFAULT_DIMENSION = new Dimension(1280, 720);
    private static final Color DEFAULT_BACKGROUND = Color.BLACK;

    private final JFrame frame;
    private final DisplayPanel panel;
    private final InputManager inputManager;

    private boolean closed = false;

    public Display() {
        this.frame = new JFrame();
        frame.setTitle(DEFAULT_TITLE);
        frame.setSize(DEFAULT_DIMENSION);
        frame.setLocationRelativeTo(null);
        this.panel = new DisplayPanel();
        panel.setBackground(DEFAULT_BACKGROUND);
        frame.setContentPane(panel);
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

    public void render(Consumer<Canvas> consumer) {
        panel.setGraphicsConsumer(consumer);
        panel.repaint();
        if(frame.isVisible())
            frame.setVisible(true);
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

    public Color getBackground() {
        return panel.getBackground();
    }

    public void setBackground(Color background) {
        panel.setBackground(background);
    }

    public InputManager getInputManager() {
        return inputManager;
    }
}

class DisplayPanel extends JPanel {

    private final Canvas canvas = new Canvas();
    private Consumer<Canvas> graphicsConsumer = g -> {};

    protected void setGraphicsConsumer(Consumer<Canvas> consumer) {
        this.graphicsConsumer = consumer;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        canvas.setGraphics((Graphics2D) g);
        graphicsConsumer.accept(canvas);
    }

    @Override
    public void paintComponents(Graphics g) {}

    @Override
    protected void paintChildren(Graphics g) {}

    @Override
    protected void paintBorder(Graphics g) {}
}
