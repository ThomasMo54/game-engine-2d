package com.motompro.gameengine2d.display;

import com.motompro.gameengine2d.gameobject.Camera;
import com.motompro.gameengine2d.gameobject.component.TransformComponent;
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
    private final Insets insets;
    private final DisplayPanel panel;
    private final InputManager inputManager;

    private boolean closed = false;
    private boolean fullscreen = false;
    private Dimension lastSize = DEFAULT_DIMENSION;
    private Camera camera;

    public Display() {
        this.frame = new JFrame();
        frame.pack();
        this.insets = frame.getInsets();
        frame.setTitle(DEFAULT_TITLE);
        frame.setSize(new Dimension((int) (DEFAULT_DIMENSION.getWidth() + insets.left + insets.right), (int) (DEFAULT_DIMENSION.getHeight() + insets.top + insets.bottom)));
        frame.setLocationRelativeTo(null);
        this.panel = new DisplayPanel(this);
        panel.setBackground(DEFAULT_BACKGROUND);
        frame.setContentPane(panel);
        this.camera = new Camera(Vector2.of(DEFAULT_DIMENSION.getWidth(), DEFAULT_DIMENSION.getHeight()));
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
        if(title == null)
            throw new IllegalArgumentException("title can't be null");
        frame.setTitle(title);
    }

    public Vector2 getSize() {
        Dimension size = frame.getSize();
        return new Vector2(size.getWidth(), size.getHeight());
    }

    public void setSize(Vector2 size) {
        if(size == null)
            throw new IllegalArgumentException("size can't be null");
        frame.setSize((int) size.getX() + insets.left + insets.right, (int) size.getY() + insets.top + insets.bottom);
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
        frame.dispose();
        frame.setUndecorated(fullscreen);
        frame.setResizable(!fullscreen);
        if(fullscreen) {
            lastSize = frame.getSize();
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            frame.setExtendedState(JFrame.NORMAL);
            frame.setSize(lastSize);
            frame.setLocationRelativeTo(null);
        }
        frame.setVisible(true);
    }

    public Vector2 getLocation() {
        Point location = frame.getLocation();
        return new Vector2(location.getX(), location.getY());
    }

    public void setLocation(Vector2 location) {
        if(location == null)
            throw new IllegalArgumentException("location can't be null");
        frame.setLocation((int) location.getX(), (int) location.getY());
    }

    public Color getBackground() {
        return panel.getBackground();
    }

    public void setBackground(Color background) {
        if(background == null)
            throw new IllegalArgumentException("background can't be null");
        panel.setBackground(background);
    }

    public Insets getInsets() {
        return insets;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        if(camera == null)
            throw new IllegalArgumentException("camera can't be null");
        this.camera = camera;
    }

    public InputManager getInputManager() {
        return inputManager;
    }
}

class DisplayPanel extends JPanel {

    private final Display display;
    private final Canvas canvas = new Canvas();
    private Consumer<Canvas> graphicsConsumer = g -> {};

    protected DisplayPanel(Display display) {
        this.display = display;
    }

    protected void setGraphicsConsumer(Consumer<Canvas> consumer) {
        this.graphicsConsumer = consumer;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        TransformComponent cameraTransform = display.getCamera().getComponent(TransformComponent.class);
        Vector2 cameraPosition = cameraTransform.getPosition();
        Vector2 viewportSize = display.getCamera().getViewportSize();
        Vector2 scaleRatio = display.getSize().copy().divide(viewportSize);
        graphics2D.translate(-cameraPosition.getX() + viewportSize.getX() / 2 * scaleRatio.getX() - display.getInsets().left, -cameraPosition.getY() + viewportSize.getY() / 2 * scaleRatio.getY() - display.getInsets().top);
        graphics2D.rotate(cameraTransform.getRotation());
        graphics2D.scale(scaleRatio.getX(), scaleRatio.getY());
        canvas.setGraphics(graphics2D);
        graphicsConsumer.accept(canvas);
    }

    @Override
    public void paintComponents(Graphics g) {}

    @Override
    protected void paintChildren(Graphics g) {}

    @Override
    protected void paintBorder(Graphics g) {}
}
