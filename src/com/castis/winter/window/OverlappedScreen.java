package com.castis.winter.window;

import java.awt.Container;

import org.dvb.event.UserEvent;

import com.alticast.navsuite.service.OverlappedDialogHandler;
import com.alticast.navsuite.service.OverlappedUIManager;

public class OverlappedScreen extends WScreen implements OverlappedDialogHandler {

    int priority = 100;

    public OverlappedScreen() {
	super.init();
	init();
    }

    public void init() {
	rootContainer = OverlappedUIManager.getInstance().createOverlappedDialog(priority, this);
    }

    public void addWindow(Window window) {
	super.addWindow(window);

	Container container = getScreenContainer(window.parent);
	if (container != null)
	    container.add(window);
    }

    public boolean handleKeyEvent(UserEvent event) {
	return handleEvent(event);
    }

    public void requestDispose(boolean dispose) {
    }

    public void setVisible(boolean visibled) {
	this.rootContainer.setVisible(visibled);
    }

}
