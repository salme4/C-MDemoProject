package com.castis.winter.event;

import java.util.EventObject;

public class TransitExplorerEvent extends Event {
    private static final long serialVersionUID = 232324254463453454L;
    public int secondIntValue;
    public int firstIntValue;
    public Object secondObjectValue;
    public Object firstObjectValue;
    
//    public int firstIntValue;
//    public int secondIntValue;
//    public Object firstObjectValue;
//    public Object secondObjectValue;
//
//    public TransitExplorerEvent(Event event) {
//	super(event);
//	this.explorerType = event.firstIntValue;
//	this.viewType = event.secondIntValue;
//	this.object = event.firstObjectValue;
//	this.subObject = event.secondObjectValue;
//    }
}
