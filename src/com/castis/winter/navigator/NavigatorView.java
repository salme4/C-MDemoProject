package com.castis.winter.navigator;

import java.awt.Graphics;

import com.castis.winter.Winter.EventType;
import com.castis.winter.event.ActionListener;
import com.castis.winter.util.StringAlign;
import com.castis.winter.window.TypedListener;
import com.castis.winter.window.WComposite;
import com.castis.winter.window.Window;

public abstract class NavigatorView extends Window {
    private static final long serialVersionUID = -2423924895300191041L;
    protected NavigatorModel model;
    protected NavigatorController controller;
    protected StringAlign alignRight = new StringAlign(10, StringAlign.JUST_RIGHT);
    protected StringAlign alignCenter = new StringAlign(10, StringAlign.JUST_CENTER);

    public NavigatorView(WComposite parent, NavigatorModel model) {
	super(parent);
	this.model = model;
    }

    public abstract void initialize();

    public abstract void drawPaint(Graphics g);

    public boolean isSelected() {
	return (!this.getEnabled() && this.isVisible());
    }

    public void setModel(NavigatorModel model) {
	this.model = model;
	controller.model = model;
    }

    public void addActionListener(ActionListener listener) {
	TypedListener typedListener = new TypedListener(listener);
	addListener(EventType.Enter, typedListener);
	addListener(EventType.Back, typedListener);
	addListener(EventType.PageChange, typedListener);
	addListener(EventType.ColorGreen, typedListener);
	addListener(EventType.FocusChange, typedListener);
	addListener(EventType.ColorYellow, typedListener);
	addListener(EventType.FocusInputBox, typedListener);
	addListener(EventType.ColorBlue, typedListener);
	addListener(EventType.ColorRed, typedListener);
    }

    protected void sendActionEvent(int actionEventType) {
	sendEvent(actionEventType);
    }

    protected void sendActionEvent(int actionEventType, boolean send) {
	sendEvent(actionEventType, send);
    }

}
