package com.castis.winter.window;

import com.castis.winter.Winter.KeyType;
import com.castis.winter.event.Event;
import com.castis.winter.event.Listener;

class EventTable {
    int[] eventTypeArray;

    Listener[] listenersArray;

    int level;

    static final int GROW_SIZE = 4;

    public Listener[] getListeners(int eventType) {
	if (eventTypeArray == null)
	    return new Listener[0];
	int count = 0;
	for (int i = 0; i < eventTypeArray.length; i++) {
	    if (eventTypeArray[i] == eventType)
		count++;
	}
	if (count == 0)
	    return new Listener[0];
	Listener[] result = new Listener[count];
	count = 0;
	for (int i = 0; i < eventTypeArray.length; i++) {
	    if (eventTypeArray[i] == eventType) {
		result[count++] = listenersArray[i];
	    }
	}
	return result;
    }

    public void hook(int eventType, Listener listener) {
	if (eventTypeArray == null)
	    eventTypeArray = new int[GROW_SIZE];
	if (listenersArray == null)
	    listenersArray = new Listener[GROW_SIZE];
	int length = eventTypeArray.length, eventTypeArrayLastIndex = length - 1;
	eventTypeArrayLastIndex = getEmptyArrayIndex(eventTypeArrayLastIndex);
	eventTypeArrayLastIndex++;
	if (eventTypeArrayLastIndex == length)
	    growArraySize(length);
	eventTypeArray[eventTypeArrayLastIndex] = eventType;
	listenersArray[eventTypeArrayLastIndex] = listener;
    }

    public int getEmptyArrayIndex(int eventTypeArrayLastIndex) {
	while (eventTypeArrayLastIndex >= 0) {
	    if (eventTypeArray[eventTypeArrayLastIndex] != 0) {
		break;
	    }
	    --eventTypeArrayLastIndex;
	}
	return eventTypeArrayLastIndex;
    }

    public void growArraySize(int length) {
	int[] newTypes = new int[length + GROW_SIZE];
	System.arraycopy(eventTypeArray, 0, newTypes, 0, length);
	eventTypeArray = newTypes;
	Listener[] newListeners = new Listener[length + GROW_SIZE];
	System.arraycopy(listenersArray, 0, newListeners, 0, length);
	listenersArray = newListeners;
    }

    public boolean hooks(int eventType) {
	if (eventTypeArray == null)
	    return false;
	for (int i = 0; i < eventTypeArray.length; i++) {
	    if (eventTypeArray[i] == eventType)
		return true;
	}
	return false;
    }

    public void sendEvent(Event castisEvent) {
	// castisEvent의 KeyCode는 System의 KeyCode를 담고 있다.
	// type 는 enevtType
	if (eventTypeArray == null)
	    return;
	level += level >= 0 ? 1 : -1;
	try {
	    for (int i = 0; i < eventTypeArray.length; i++) {
		if (castisEvent.type ==KeyType.None)
		    return;
		if (eventTypeArray[i] == castisEvent.type) {
		    Listener listener = listenersArray[i];
		    if (listener != null)
			listener.handleEvent(castisEvent);
		}
	    }
	} finally {
	    boolean compact = level < 0;
	    level -= level >= 0 ? 1 : -1;
	    if (compact && level == 0) {
		int index = 0;
		for (int i = 0; i < eventTypeArray.length; i++) {
		    if (eventTypeArray[i] != 0) {
			eventTypeArray[index] = eventTypeArray[i];
			listenersArray[index] = listenersArray[i];
			index++;
		    }
		}
		for (int i = index; i < eventTypeArray.length; i++) {
		    eventTypeArray[i] = 0;
		    listenersArray[i] = null;
		}
	    }
	}
    }

    public int size() {
	if (eventTypeArray == null)
	    return 0;
	int count = 0;
	for (int i = 0; i < eventTypeArray.length; i++) {
	    if (eventTypeArray[i] != 0)
		count++;
	}
	return count;
    }

    void remove(int index) {
	if (level == 0) {
	    int end = eventTypeArray.length - 1;
	    System.arraycopy(eventTypeArray, index + 1, eventTypeArray, index, end - index);
	    System.arraycopy(listenersArray, index + 1, listenersArray, index, end - index);
	    index = end;
	} else {
	    if (level > 0)
		level = -level;
	}
	eventTypeArray[index] = 0;
	listenersArray[index] = null;
    }

    public void unhook(int eventType, Listener listener) {
	if (eventTypeArray == null)
	    return;
	for (int i = 0; i < eventTypeArray.length; i++) {
	    if (eventTypeArray[i] == eventType && listenersArray[i] == listener) {
		remove(i);
		return;
	    }
	}
    }

    /*
     * public void unhook (int eventType, EventListener listener) { if (types ==
     * null) return; for (int i=0; i<types.length; i++) { if (types [i] ==
     * eventType) { if (listeners [i] instanceof TypedListener) { TypedListener
     * typedListener = (TypedListener) listeners [i]; if
     * (typedListener.getEventListener () == listener) { remove (i); return; } }
     * } } }
     */

}
