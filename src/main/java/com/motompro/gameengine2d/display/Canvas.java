package com.motompro.gameengine2d.display;

import javax.swing.*;
import java.awt.*;

public class Canvas {

    private static final Color DEFAULT_BACKGROUND = Color.BLACK;

    private final JPanel panel;

    public Canvas() {
        this.panel = new JPanel();
        panel.setBackground(DEFAULT_BACKGROUND);
    }

    public JPanel getPanel() {
        return panel;
    }
}
