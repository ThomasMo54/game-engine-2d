package com.motompro.gameengine2d.manager;

import com.motompro.gameengine2d.math.Vector2;

import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class InputManager implements KeyListener, MouseListener, MouseMotionListener {

    private final Set<Integer> PRESSED_KEYS = new HashSet<>();
    private final Set<Integer> PRESSED_MOUSE_BUTTONS = new HashSet<>();

    private final Vector2 mousePosition = new Vector2(0, 0);
    private boolean dragged = false;

    public boolean isKeyPressed(int keyCode) {
        return PRESSED_KEYS.contains(keyCode);
    }

    public boolean isLeftMouseButtonPressed() {
        return PRESSED_MOUSE_BUTTONS.contains(MouseEvent.BUTTON1);
    }

    public boolean isRightMouseButtonPressed() {
        return PRESSED_MOUSE_BUTTONS.contains(MouseEvent.BUTTON2);
    }

    public boolean isMiddleMouseButtonPressed() {
        return PRESSED_MOUSE_BUTTONS.contains(MouseEvent.BUTTON3);
    }

    public boolean isMouseDragged() {
        return dragged;
    }

    public Vector2 getMousePosition() {
        return mousePosition.copy();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        PRESSED_KEYS.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        PRESSED_KEYS.remove(e.getKeyCode());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        PRESSED_MOUSE_BUTTONS.add(e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        PRESSED_MOUSE_BUTTONS.remove(e.getButton());
        dragged = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dragged = true;
        mousePosition.set(e.getX(), e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition.set(e.getX(), e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
}
