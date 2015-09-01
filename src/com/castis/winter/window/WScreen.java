package com.castis.winter.window;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.util.Hashtable;

import org.dvb.event.UserEvent;

import com.castis.winter.Winter;
import com.castis.winter.Winter.KeyType;
import com.castis.winter.event.Event;
import com.castis.winter.util.Logger;

public abstract class WScreen implements Screen {
    Container rootContainer;

    Window window;

    Hashtable composites;

    Event[] eventQueue;

    int freeSlot;

    int[] indexTable;

    Window[] windowTable;

    static final int GROW_SIZE = 1024;

    public void init() {
	/* Initialize the Widget Table */
	composites = new Hashtable();
	indexTable = new int[GROW_SIZE];
	windowTable = new Window[GROW_SIZE];
	for (int i = 0; i < GROW_SIZE - 1; i++)
	    indexTable[i] = i + 1;
	indexTable[GROW_SIZE - 1] = -1;
    }

    public Container getScreenContainer(WComposite composite) {
	return (Container) composites.get(composite);
    }

    public void dispose() {
	rootContainer.removeAll();
	rootContainer = null;
	composites.clear();
	composites = null;
	windowTable = null;
	eventQueue = null;
	window = null;
    }

    public void clear() {
	rootContainer.getParent().getGraphics().clearRect(0, 0, 720, 480);
    }

    public void addWComposite(WComposite composite) {
	Container container = createWCompositeContainer();
	container.add(composite);
	rootContainer.add(container);
	composites.put(composite, container);
    }

    private Container createWCompositeContainer() {
	Container wCompositeContainer = new Container();
	wCompositeContainer.setBounds(Winter.Resoultion720x480);
	wCompositeContainer.setVisible(false);
	return wCompositeContainer;
    }

    public void addWindow(Window window) {
	if (freeSlot == -1) {
	    int length = (freeSlot = indexTable.length) + GROW_SIZE;
	    int[] newIndexTable = new int[length];
	    Window[] newWindowTable = new Window[length];
	    System.arraycopy(indexTable, 0, newIndexTable, 0, freeSlot);
	    System.arraycopy(windowTable, 0, newWindowTable, 0, freeSlot);
	    for (int i = freeSlot; i < length - 1; i++)
		newIndexTable[i] = i + 1;
	    newIndexTable[length - 1] = -1;
	    indexTable = newIndexTable;
	    windowTable = newWindowTable;
	}
	int oldSlot = freeSlot;
	freeSlot = indexTable[oldSlot];
	indexTable[oldSlot] = -2;
	windowTable[oldSlot] = window;
    }

    public boolean handleEvent(UserEvent userEvent) {
	Event castisEvent = new Event();
	castisEvent.keyCode = userEvent.getCode();
	if (userEvent.getType() == KeyEvent.KEY_PRESSED) {
	    for (int i = 0; i < windowTable.length; i++) {
		if (windowTable[i] != null && windowTable[i].getEnabled()) {
		    windowTable[i].sendKeyEventFromSystemToEnableWindow(KeyType.KeyUp, castisEvent);
		    // Logger.I(this, "WindowTable : " + windowTable[i].getClass());
		}
	    }
	} else if (userEvent.getType() == KeyEvent.KEY_RELEASED) {
	    return false;
	}
	return runDeferredEvents();
    }

    public void postEvent(Event event) {
	/*
	 * Place the event at the end of the event queue. This code is always
	 * called in the Display's thread so it must be re-enterant but does not
	 * need to be synchronized.
	 */
	if (eventQueue == null)
	    eventQueue = new Event[4];
	int index = 0;
	int length = eventQueue.length;
	while (index < length) {
	    if (eventQueue[index] == null)
		break;
	    index++;
	}
	if (index == length) {
	    Event[] newQueue = new Event[length + 4];
	    System.arraycopy(eventQueue, 0, newQueue, 0, length);
	    eventQueue = newQueue;
	}
	eventQueue[index] = event;
    }

    public boolean runDeferredEvents() {
	boolean run = false;
	/*
	 * Run deferred events. This code is always called in the Display's
	 * thread so it must be re-enterant but need not be synchronized.
	 */
	while (eventQueue != null) {
	    /* Take an event off the queue */
	    Event event = eventQueue[0];
	    if (event == null)
		break;
	    int length = eventQueue.length;
	    System.arraycopy(eventQueue, 1, eventQueue, 0, --length);
	    eventQueue[length] = null;
	    /* Run the event */
	    WComponent wComponent = event.wComponent;
	    if (wComponent != null && !wComponent.isDisposed()) {
		run = true;
		wComponent.sendEventToEventTable(event);
		// System.out.println("keyPress Event >>>>"+wComponent.getClass().getName());
	    }
	    /*
	     * At this point, the event queue could be null due to a recursive
	     * invocation when running the event.
	     */
	}
	/* Clear the queue */
	eventQueue = null;
	return run;
    }
}
