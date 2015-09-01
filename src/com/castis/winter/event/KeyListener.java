package com.castis.winter.event;

import java.util.EventListener;

public interface KeyListener extends EventListener{
    /**
     * Sent when a key is pressed on the remote control.
     * 
     * @param e
     *            an event containing information about the key press
     */
    public void keyPressed(KeyEvent e);

    /**
     * Sent when a key is released on the remote control.
     * 
     * @param e
     *            an event containing information about the key release
     */
    public void keyReleased(KeyEvent e);
}
