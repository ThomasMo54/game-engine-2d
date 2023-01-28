package com.motompro.gameengine2d.display;

import com.motompro.gameengine2d.gameobject.component.TransformComponent;
import com.motompro.gameengine2d.manager.InputManager;
import com.motompro.gameengine2d.math.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
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
        frame.setSize(new Dimension((int) DEFAULT_DIMENSION.getWidth(), (int) (DEFAULT_DIMENSION.getHeight() + insets.top)));
        frame.setLocationRelativeTo(null);
        this.panel = new DisplayPanel(this);
        panel.setBackground(DEFAULT_BACKGROUND);
        frame.setContentPane(panel);
        this.camera = new Camera(Vector2.of(DEFAULT_DIMENSION.getWidth(), DEFAULT_DIMENSION.getHeight()));
        this.inputManager = new InputManager();
        frame.addKeyListener(inputManager);
        panel.addMouseListener(inputManager);
        panel.addMouseMotionListener(inputManager);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                e.getWindow().dispose();
                closed = true;
            }
        });
    }

    public void render(Consumer<Canvas> graphicsConsumer, Consumer<HUD> hudConsumer) {
        panel.setConsumers(graphicsConsumer, hudConsumer);
        panel.repaint();
        if(frame.isVisible())
            frame.setVisible(true);
    }

    public void render(Consumer<Canvas> graphicsConsumer) {
        render(graphicsConsumer, hud -> {});
    }

    public void close() {
        frame.dispose();
        closed = true;
    }

    public void show() {
        frame.setVisible(true);
        camera.setViewportSize(getCanvasSize());
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
        frame.setSize((int) size.getX(), (int) size.getY() + insets.top);
    }

    public Vector2 getCanvasSize() {
        Dimension size = panel.getSize();
        return new Vector2(size.getWidth(), size.getHeight());
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
    private final HUD hud = new HUD();
    private Consumer<Canvas> graphicsConsumer = g -> {};
    private Consumer<HUD> hudConsumer = hud -> {};

    protected DisplayPanel(Display display) {
        this.display = display;
    }

    protected void setConsumers(Consumer<Canvas> graphicsConsumer, Consumer<HUD> hudConsumer) {
        this.graphicsConsumer = graphicsConsumer;
        this.hudConsumer = hudConsumer;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        Camera camera = display.getCamera();
        TransformComponent cameraTransform = camera.getComponent(TransformComponent.class);
        Vector2 cameraPosition = cameraTransform.getPosition();
        Vector2 viewportSize = camera.getViewportSize();
        Vector2 scaleRatio = display.getCanvasSize().copy().divide(viewportSize);
        graphics2D.translate(-cameraPosition.getX() + viewportSize.getX() / 2 * scaleRatio.getX(), -cameraPosition.getY() + viewportSize.getY() / 2 * scaleRatio.getY());
        graphics2D.rotate(cameraTransform.getRotation());
        graphics2D.scale(scaleRatio.getX(), scaleRatio.getY());
        canvas.setGraphics(graphics2D);
        graphicsConsumer.accept(canvas);
        graphics2D.setTransform(new AffineTransform());
        graphics2D.scale(scaleRatio.getX(), scaleRatio.getY());
        hud.setGraphics(graphics2D);
        hudConsumer.accept(hud);
    }

    @Override
    public void paintComponents(Graphics g) {}

    @Override
    protected void paintChildren(Graphics g) {}

    @Override
    protected void paintBorder(Graphics g) {}
}
