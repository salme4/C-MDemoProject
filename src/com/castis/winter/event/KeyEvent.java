package com.castis.winter.event;

import java.util.EventObject;

import com.castis.winter.window.WComponent;

public class KeyEvent extends EventObject {

    private static final long serialVersionUID = 4534922018352337729L;

    public int keyCode;

    public WComponent wComponent;

    public KeyEvent(Event e) {
	super(e);
	this.keyCode = e.keyCode;
	this.wComponent = e.wComponent;
    }

    public String toString() {
	String string = super.toString();
	return string.substring(0, string.length() - 1) // remove trailing '}'
		+ " keyCode=" + keyCode + "}";
    }

}
