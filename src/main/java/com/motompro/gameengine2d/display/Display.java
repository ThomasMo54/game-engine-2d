package com.motompro.gameengine2d.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Display {

    private static final String DEFAULT_TITLE = "Display";
    private static final Dimension DEFAULT_DIMENSION = new Dimension(1280, 720);

    private final JFrame frame;
    private final Canvas canvas;

    private boolean closed = false;

    public Display() {
        this.frame = new JFrame();
        frame.setTitle(DEFAULT_TITLE);
        frame.setSize(DEFAULT_DIMENSION);
        frame.setLocationRelativeTo(null);
        this.canvas = new Canvas();
        frame.setContentPane(canvas.getPanel());

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                e.getWindow().dispose();
                closed = true;
            }
        });
    }

    public void show() {
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(true);
    }

    public boolean isVisible() {
        return frame.isVisible();
    }

    public boolean isClosed() {
        return closed;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
