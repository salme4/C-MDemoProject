package com.castis.winter.window;

import java.awt.Component;

import com.castis.winter.event.Event;
import com.castis.winter.event.Listener;

public abstract class WComponent extends Component {
    private static final long serialVersionUID = 8336607192938884308L;

    Screen screen;

    EventTable eventTable;

    /**
     * Prevents uninitialized instances from being created outside the package.
     */
    WComponent() {
    }

    public WComponent(WComponent component) {
	screen = component.screen;
    }

    public Screen getScreen() {
	return screen;
    }

    public void dispose() {
	release();
    }

    void release() {
	screen.dispose();
    }

    protected void addListener(int eventType, Listener listener) {
	if (eventTable == null)
	    eventTable = new EventTable();
	eventTable.hook(eventType, listener);
    }

    protected void removeListener(int eventType, Listener listener) {
	if (eventTable == null)
	    return;
	eventTable.unhook(eventType, listener);
    }

    boolean sendKeyEventFromSystemToEnableWindow(int type, Event castisEvent) {
	sendEvent(type, castisEvent);
	return true;
    }

    protected void sendEvent(int eventType, Event event) {
	sendEvent(eventType, event, true);
    }

    protected void sendKeyEventFromSystem(int eventType, Event event, boolean send) {
	sendEvent(eventType, event, send);
    }

    protected void sendEvent(int eventType) {
	sendEvent(eventType, null, false);
    }

    protected void sendEvent(int eventType, boolean send) {
	sendEvent(eventType, null, send);
    }

    void sendEvent(int eventType, Event castisEvent, boolean send) {
	if (eventTable == null) {
	    return;
	}

	if (castisEvent == null)
	    castisEvent = new Event();
	castisEvent.wComponent = this;
	castisEvent.type = eventType;

	if (send)
	    sendEventToEventTable(castisEvent);
	else
	    screen.postEvent(castisEvent);
    }

    void sendEventToEventTable(Event castisEvent) {
	if (eventTable != null)
	    eventTable.sendEvent(castisEvent);
    }

    boolean isDisposed() {
	return false;
    }
}
